package com.sleepy.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sleepy.goods.common.Constant;
import com.sleepy.goods.dto.*;
import com.sleepy.goods.entity.AddressEntity;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.entity.OrderEntity;
import com.sleepy.goods.entity.UserEntity;
import com.sleepy.goods.repository.AddressRepository;
import com.sleepy.goods.repository.GoodsRepository;
import com.sleepy.goods.repository.OrderRepository;
import com.sleepy.goods.repository.UserRepository;
import com.sleepy.goods.service.OrderService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.CartVO;
import com.sleepy.goods.vo.OrderVO;
import com.sleepy.goods.vo.cart.CartSettlementVO;
import com.sleepy.goods.vo.order.OrderNewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单 服务
 *
 * @author gehoubao
 * @create 2020-02-14 20:26
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public CommonDTO<OrderEntity> getOrderList(String userId) {
        List<OrderEntity> data = orderRepository.findByUserId(userId);
        return getOrderDetailResult(data);
    }

    @Override
    public CommonDTO<OrderEntity> saveOrder(OrderNewVO vo) throws Exception {
        CommonDTO<OrderEntity> result = new CommonDTO<>();
        OrderEntity entity = new OrderEntity(vo);
        UserEntity user = userRepository.findByUserId(vo.getUserId()).get();
        if (StringUtil.isNullOrEmpty(user.getCartInfo())) {
            StringUtil.throwExceptionInfo("购物车为空!");
        }
        JSONObject carts = JSON.parseObject(user.getCartInfo());
        if (vo.getGoodsIds().size() > 0) {
            StringBuilder goodsString = new StringBuilder();
            double goodsTotalPrice = 0;
            Map<String, GoodsEntity> goods = goodsRepository.findAllByGoodsIdIn(vo.getGoodsIds()).stream().collect(Collectors.toMap(GoodsEntity::getGoodsId, p -> p));
            for (String goodsId : vo.getGoodsIds()) {
                JSONObject item = carts.getJSONObject(goodsId);
                if (vo.getSettlementTime().compareTo(goods.get(goodsId).getUpdateTime()) > 0) {
                    goodsTotalPrice += item.getIntValue("selectedNum") * goods.get(goodsId).getGoodsPriceNow();

                    goodsString.append(StringUtil.getSplitString(Constant.PROPERTY_SPLIT_SYMBOL,
                            goodsId,
                            String.valueOf(item.getIntValue("selectedNum")),
                            String.valueOf(goods.get(goodsId).getGoodsPriceNow()),
                            ""));
                    goodsString.append(Constant.COMMA);
                    carts.remove(goodsId);
                } else {
                    StringUtil.throwExceptionInfo("商品数据变化，请重新确认订单信息");
                }
            }
            if (StringUtil.isNotNullOrEmpty(vo.getComment())) {
                entity.setComment(vo.getComment());
            }
            entity.setGoods(goodsString.substring(0, goodsString.length() - 1));
            entity.setGoodsTotalPrice(goodsTotalPrice);
            entity.setTotalPrice(goodsTotalPrice);
            entity.setOrderTime(StringUtil.getDateString(new Date()));
            result.setResult(orderRepository.saveAndFlush(entity));

            user.setCartInfo(JSON.toJSONString(carts));
            userRepository.saveAndFlush(user);
        } else {
            StringUtil.throwExceptionInfo("订单商品不能为空");
        }
        return result;
    }

    @Override
    public CommonDTO<CartDTO> getCartList(String userId) {
        CommonDTO<CartDTO> result = new CommonDTO<>();
        UserEntity entity = userRepository.findByUserId(userId).get();
        String cartString = entity.getCartInfo();
        if (StringUtil.isNotNullOrEmpty(cartString)) {
            JSONObject carts = JSON.parseObject(cartString);
            Map<String, CartDTO> cartsMap = StringUtil.jsonObjectToMap(carts);
            List<CartDTO> data = new ArrayList<>(cartsMap.values());
            List<String> goodsIds = new ArrayList<>(cartsMap.keySet());
            List<GoodsEntity> goods = goodsRepository.findAllByGoodsIdIn(goodsIds);

            result.setResultList(data);
            result.setExtra(StringUtil.getNewExtraMap(new ExtraDTO("goods", goods)));
            result.setTotal((long) data.size());
        }
        return result;
    }

    @Override
    public CommonDTO<CartDTO> updateCart(CartVO vo) throws Exception {
        CommonDTO<CartDTO> result = new CommonDTO<>();
        if (!StringUtil.isNullOrEmpty(vo.getUserId())) {
            UserEntity entity = userRepository.findByUserId(vo.getUserId()).get();
            String cartString = entity.getCartInfo();
            JSONObject carts = JSON.parseObject(cartString);
            if (carts == null) {
                carts = new JSONObject();
            }
            CartDTO cart = JSONObject.parseObject(JSON.toJSONString(carts.get(vo.getGoodsId())), CartDTO.class);

            if (cart == null) {
                cart = new CartDTO();
                cart.setGoodsId(vo.getGoodsId());
                cart.setSelectedNum("0");
            }

            int storageNum = goodsRepository.findById(vo.getGoodsId()).get().getStorageNum();
            int selectedNum = Integer.parseInt(cart.getSelectedNum()) + vo.getValueChange();
            if (selectedNum < storageNum) {
                cart.setSelectedNum(selectedNum + "");
                if (selectedNum > 0) {
                    carts.put(vo.getGoodsId(), cart);
                } else {
                    carts.remove(vo.getGoodsId());
                }
            } else {
                throw new Exception("库存不足");
            }
            entity.setCartInfo(JSON.toJSONString(carts));
            userRepository.saveAndFlush(entity);
            result.setResult(cart);
        }
        return result;
    }

    @Override
    public CommonDTO<CartDTO> deleteGoodsInCart(CartVO vo) {
        CommonDTO<CartDTO> result = new CommonDTO<>();
        UserEntity entity = userRepository.findByUserId(vo.getUserId()).get();
        String cartString = entity.getCartInfo();
        if (StringUtil.isNotNullOrEmpty(cartString)) {
            JSONObject carts = JSON.parseObject(cartString);
            vo.getGoodsIdsDeleted().forEach(goodsID -> {
                if (carts.get(goodsID) != null) {
                    carts.remove(goodsID);
                }
            });
            entity.setCartInfo(JSON.toJSONString(carts));
            userRepository.saveAndFlush(entity);
            result.setResultList(new ArrayList<>(StringUtil.jsonObjectToMap(carts).values()));
        }
        return result;
    }

    @Override
    public CommonDTO<OrderEntity> getOrderListByCond(OrderVO vo) {
        if (vo.getDeliveryStatus() != null) {
            List<OrderEntity> data = orderRepository.findAllByDeliveryStatusOrderByOrderTimeDesc(vo.getDeliveryStatus());
            return getOrderDetailResult(data);
        }
        if (StringUtil.isNotNullOrEmpty(vo.getOrderId())) {
            OrderEntity entity = orderRepository.findById(vo.getOrderId()).get();
            return getOrderDetailResult(Arrays.asList(entity));
        } else {
            List<OrderEntity> data = orderRepository.findAllByOrderByOrderTimeDesc();
            return getOrderDetailResult(data);
        }
    }

    @Override
    public CommonDTO<SettlementDTO> settlement(CartSettlementVO vo) throws Exception {
        CommonDTO<SettlementDTO> result = new CommonDTO<>();
        Double totalPrice, goodsTotalPrice = 0d;
        Map<String, SettlementGoodsPriceDTO> goodsPriceMap = new HashMap<>(vo.getGoods().size());
        if (vo.getGoods().size() > 0) {
            List<GoodsEntity> goodsList = goodsRepository.findAllByGoodsIdIn(new ArrayList<>(vo.getGoods().keySet()));
            for (GoodsEntity goods : goodsList) {
                SettlementGoodsPriceDTO price = new SettlementGoodsPriceDTO();
                price.setGoodsId(goods.getGoodsId());
                price.setPriceNow(goods.getGoodsPriceNow());
                price.setAmount(vo.getGoods().get(goods.getGoodsId()));
                price.setTotalPrice(StringUtil.formatPriceNum(vo.getGoods().get(goods.getGoodsId()) * goods.getGoodsPriceNow()));
                goodsPriceMap.put(goods.getGoodsId(), price);
                goodsTotalPrice += price.getTotalPrice();
            }
            totalPrice = goodsTotalPrice;
            SettlementDTO data = new SettlementDTO();
            data.setTotalPrice(StringUtil.formatPriceNum(totalPrice));
            data.setGoodsTotalPrice(StringUtil.formatPriceNum(goodsTotalPrice));
            data.setCouponPrice(StringUtil.formatPriceNum(0d));
            data.setDeliveryPrice(StringUtil.formatPriceNum(0d));
            result.setResult(data);
            result.setExtra(StringUtil.getNewExtraMap(new ExtraDTO("settlementTime", StringUtil.getDateString(new Date())),
                    new ExtraDTO("settlementDetail", goodsPriceMap)));
        } else {
            StringUtil.throwExceptionInfo("购物车结算商品数据goods不能为空");
        }
        return result;
    }

    private CommonDTO<OrderEntity> getOrderDetailResult(List<OrderEntity> data) {
        CommonDTO<OrderEntity> result = new CommonDTO<>();
        Set<String> goodsIds = new HashSet<>();
        Set<String> addressIds = new HashSet<>();
        data.forEach(d -> {
            for (String s : d.getGoods().split(",")) {
                goodsIds.add(s.split(":")[0]);
                addressIds.add(d.getAddressId());
            }
        });
        List<GoodsEntity> goods = goodsRepository.findAllByGoodsIdIn(new ArrayList<>(goodsIds));
        List<AddressEntity> address = addressRepository.findAllByAddressIdIn(new ArrayList<>(addressIds));

        result.setResultList(data);
        result.setExtra(StringUtil.getNewExtraMap(
                new ExtraDTO("goods", goods.stream().collect(Collectors.toMap(GoodsEntity::getGoodsId, p -> p))),
                new ExtraDTO("address", address.stream().collect(Collectors.toMap(AddressEntity::getAddressId, p -> p)))));
        result.setTotal((long) data.size());
        return result;
    }
}