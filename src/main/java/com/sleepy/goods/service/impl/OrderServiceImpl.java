package com.sleepy.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sleepy.goods.dto.*;
import com.sleepy.goods.dto.order.SingleGoodOrderDTO;
import com.sleepy.goods.entity.*;
import com.sleepy.goods.repository.*;
import com.sleepy.goods.service.OrderService;
import com.sleepy.goods.source.DataSourceGetter;
import com.sleepy.goods.source.DataSourceSetter;
import com.sleepy.goods.util.DataUtil;
import com.sleepy.goods.util.HPCalcUtil;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.cart.CartSettlementVO;
import com.sleepy.goods.vo.cart.CartVO;
import com.sleepy.goods.vo.order.OrderNewVO;
import com.sleepy.goods.vo.order.OrderSearchVO;
import com.sleepy.goods.vo.order.OrderStatisticVO;
import com.sleepy.goods.vo.order.UpdateStatusVO;
import com.sleepy.jpql.JpqlExecutor;
import com.sleepy.jpql.JpqlResultSet;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private static final String ES_SO_STORE_ORDER_INDEX = "so_store_order_index";

    @Autowired
    DataSourceGetter dataSourceGetter;

    @Autowired
    DataSourceSetter dataSourceSetter;

    @Autowired
    JestClient jestClient;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    GoodsSpecRepository goodsSpecRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    JpqlExecutor jpqlExecutor;

    @Override
    public CommonDTO<OrderEntity> getOrderListByUserId(OrderSearchVO vo) {
        Map<String, Object> params = StringUtil.newParamsMap(new MapDTO("userId", vo.getUserId()), new MapDTO("limit", vo.getPageSize()),
                new MapDTO("offset", (vo.getPage() - 1) * vo.getPageSize()));
        if (vo.getDeliveryStatus() != null) {
            params.put("deliveryStatus", vo.getDeliveryStatus().toString());
        }
        JpqlResultSet set = jpqlExecutor.exec("order.findOrder", params, OrderEntity.class);
        List<OrderEntity> data = set.getResultList();
        CommonDTO<OrderEntity> result = getOrderDetailResult(data);
        return result;
    }

    @Override
    public CommonDTO<OrderEntity> getOrderByOrderId(String orderId) {
        JpqlResultSet set = jpqlExecutor.exec("order.findOrder",
                StringUtil.newParamsMap(new MapDTO("orderId", orderId)), OrderEntity.class);
        List<OrderEntity> data = set.getResultList();
        CommonDTO<OrderEntity> result = getOrderDetailResult(data);
        return result;
    }

    @Override
    public CommonDTO<OrderEntity> saveOrder(OrderNewVO vo) throws Exception {
        if (vo.getSpecIds() == null || vo.getSpecIds().size() <= 0) {
            StringUtil.throwExceptionInfo("订单商品不能为空");
        }

        UserEntity user = dataSourceGetter.getUser(vo.getUserId());
        if (StringUtil.isNullOrEmpty(user.getCartInfo())) {
            StringUtil.throwExceptionInfo("购物车为空!");
        }

        AddressEntity address = dataSourceGetter.getAddress(vo.getAddressId());
        Map<Long, GoodsSpecEntity> goodsSpecMap = dataSourceGetter.getGoodSpecMap(vo.getSpecIds());

        OrderEntity entity = new OrderEntity(vo, address);
        JSONObject carts = JSON.parseObject(user.getCartInfo());
        double goodsTotalPrice = 0;
        List<SingleGoodOrderDTO> goodsInOrder = new ArrayList<>();

        for (long specId : vo.getSpecIds()) {
            JSONObject item = carts.getJSONObject(String.valueOf(specId));
            GoodsSpecEntity spec = goodsSpecMap.get(specId);
            // 校验商品是否被修改
            if (vo.getSettlementTime().compareTo(spec.getUpdateTime()) <= 0) {
                StringUtil.throwExceptionInfo("商品数据变化，请重新确认订单信息");
            }
            // 计算商品总价
            goodsTotalPrice = HPCalcUtil.add(goodsTotalPrice, HPCalcUtil.mul(item.getIntValue("selectedNum"), spec.getGoodsPriceNow()));
            // 添加订单商品
            SingleGoodOrderDTO single = new SingleGoodOrderDTO(specId, item.getIntValue("selectedNum"), spec.getGoodsPriceNow(), "");
            goodsInOrder.add(single);
            // 购物车中清除该商品
            carts.remove(specId);
        }

        entity.setGoods(DataUtil.getGoodsStringForOrder(goodsInOrder));
        entity.setGoodsTotalPrice(goodsTotalPrice);
        // todo: 订单总价 = 商品总价 + 运费 - 优惠券
        entity.setTotalPrice(goodsTotalPrice);
        entity.setOrderTime(StringUtil.currentTimeStr());
        OrderEntity order = orderRepository.saveAndFlush(entity);

        try {
            saveToEs(order);
        } catch (IOException e) {
            orderRepository.delete(entity);
            StringUtil.throwExceptionInfo("创建订单失败，请检查ES存取操作");
        }
        user.setCartInfo(JSON.toJSONString(carts));
        userRepository.saveAndFlush(user);

        CommonDTO<OrderEntity> result = new CommonDTO<>();
        result.setResult(order);
        return result;
    }

    @Override
    public CommonDTO<CartDTO> getCartList(String userId) {
        CommonDTO<CartDTO> result = new CommonDTO<>();
        UserEntity entity = dataSourceGetter.getUser(userId);
        String cartString = entity.getCartInfo();
        if (StringUtil.isNotNullOrEmpty(cartString)) {
            JSONObject carts = JSON.parseObject(cartString);
            Map<String, CartDTO> cartsMap = StringUtil.jsonObjectToMap(carts);
            List<CartDTO> data = new ArrayList<>(cartsMap.values());
            List<String> goodsIds = new ArrayList<>(cartsMap.keySet());
            List<GoodsEntity> goods = goodsRepository.findAllByGoodsIdIn(goodsIds);

            result.setResultList(data);
            result.setExtra(StringUtil.getNewExtraMap(new MapDTO("goods", goods)));
            result.setTotal((long) data.size());
        }
        return result;
    }

    @Override
    public CommonDTO<CartDTO> updateCart(CartVO vo) throws Exception {
        if (!StringUtil.isNullOrEmpty(vo.getUserId())) {
            UserEntity user = dataSourceGetter.getUser(vo.getUserId());
            Map<Long, CartDTO> cartMap = dataSourceGetter.getCartMap(user);
            CartDTO cart = cartMap.get(vo.getSpecId());
            if (cart == null) {
                cart = new CartDTO(vo.getSpecId());
            }

            int stock = dataSourceGetter.getGoodSpec(vo.getSpecId()).getStock();
            int selectedNum = cart.getSelectedNum() + vo.getValueChange();
            if (stock < selectedNum) {
                throw new Exception("库存不足");
            }

            cart.setSelectedNum(selectedNum);
            if (selectedNum > 0) {
                cartMap.put(vo.getSpecId(), cart);
            } else {
                cartMap.remove(vo.getSpecId());
            }

            user.setCartInfo(JSON.toJSONString(cartMap));
            userRepository.saveAndFlush(user);
        }
        return getCartList(vo.getUserId());
    }

    @Override
    public CommonDTO<CartDTO> deleteGoodsInCart(CartVO vo) {
        CommonDTO<CartDTO> result = new CommonDTO<>();
        UserEntity user = dataSourceGetter.getUser(vo.getUserId());
        Map<Long, CartDTO> cart = dataSourceGetter.getCartMap(user);
        vo.getSpecIdsDeleted().forEach(specId -> {
            if (cart.get(specId) != null) {
                cart.remove(specId);
            }
        });
        dataSourceSetter.saveCart(user, cart);

        result.setResultList(new ArrayList<>(cart.values()));
        return result;
    }

    @Override
    public CommonDTO<OrderEntity> getOrderListByCond(OrderSearchVO vo) {
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
        if (vo.getGoodSpecMap().size() <= 0) {
            StringUtil.throwExceptionInfo("购物车结算商品数据goods不能为空");
        }
        CommonDTO<SettlementDTO> result = new CommonDTO<>();
        Double totalPrice, goodsTotalPrice = 0d;
        Map<Long, SettlementGoodsPriceDTO> goodsPriceMap = new HashMap<>(vo.getGoodSpecMap().size());

        List<GoodsSpecEntity> goodsSpecList = dataSourceGetter.getGoodSpecList(new ArrayList<>(vo.getGoodSpecMap().keySet()));
        for (GoodsSpecEntity spec : goodsSpecList) {
            SettlementGoodsPriceDTO price = new SettlementGoodsPriceDTO();
            price.setGoodSpecId(spec.getId());
            price.setPriceNow(spec.getGoodsPriceNow());
            price.setAmount(vo.getGoodSpecMap().get(spec.getId()));
            price.setTotalPrice(StringUtil.formatPriceNum(HPCalcUtil.mul(vo.getGoodSpecMap().get(spec.getId()), spec.getGoodsPriceNow())));

            goodsPriceMap.put(spec.getId(), price);
            goodsTotalPrice = HPCalcUtil.add(goodsTotalPrice, price.getTotalPrice());
        }
        totalPrice = goodsTotalPrice;

        SettlementDTO data = new SettlementDTO();
        data.setTotalPrice(StringUtil.formatPriceNum(totalPrice));
        data.setGoodsTotalPrice(StringUtil.formatPriceNum(goodsTotalPrice));
        data.setCouponPrice(StringUtil.formatPriceNum(0d));
        data.setDeliveryPrice(StringUtil.formatPriceNum(0d));

        result.setResult(data);
        result.setExtra(StringUtil.getNewExtraMap(new MapDTO("settlementTime", StringUtil.getDateString(new Date())),
                new MapDTO("settlementDetail", goodsPriceMap)));
        return result;
    }

    @Override
    public CommonDTO<OrderEntity> updateOrderStatus(UpdateStatusVO vo) {
        CommonDTO<OrderEntity> result = new CommonDTO<>();
        OrderEntity entity = orderRepository.findById(vo.getOrderId()).get();
        entity.setDeliveryStatus(vo.getStatus());
        result.setResult(orderRepository.saveAndFlush(entity));
        return result;
    }

    @Override
    public CommonDTO<OrderEntity> assignOrder(String status) {
        return null;
    }

    @Override
    public CommonDTO<String> statistic(OrderStatisticVO vo) throws Exception {
        String startTime = StringUtil.getDateString(StringUtil.getDateAgoFromNow(vo.getStatisticDaysBefore()));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        ((BoolQueryBuilder) boolQueryBuilder).must().addAll(
                Arrays.asList(QueryBuilders.rangeQuery("orderTime").from(startTime)));
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(0);
        searchSourceBuilder.aggregation(AggregationBuilders.dateHistogram("day")
                .field("orderTime").dateHistogramInterval(DateHistogramInterval.DAY).format("yyyy-MM-dd").minDocCount(0)
                .extendedBounds(new ExtendedBounds(startTime.substring(0, 10), StringUtil.getDateString(new Date()).substring(0, 10)))
                .subAggregation(AggregationBuilders.sum("amount").field("totalPrice")));
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(ES_SO_STORE_ORDER_INDEX).addType("order").build();
        List<Map<String, Object>> orderStatistic = new ArrayList<>();
        List<Map<String, Object>> incomeStatistic = new ArrayList<>();
        JestResult jestResult = null;
        try {
            jestResult = jestClient.execute(search);
        } catch (IOException e) {
            StringUtil.throwExceptionInfo("查询es失败: " + jestResult.getErrorMessage());
        }
        ((SearchResult) jestResult).getAggregations().getDateHistogramAggregation("day").getBuckets().
                stream().forEach(o -> {
            orderStatistic.add(StringUtil.getNewExtraMap(new MapDTO(o.getTimeAsString(), o.getCount())));
            incomeStatistic.add(StringUtil.getNewExtraMap(new MapDTO(o.getTimeAsString(), o.getSumAggregation("amount").getSum())));
        });
        CommonDTO<String> result = new CommonDTO<>();
        result.setExtra(StringUtil.getNewExtraMap(new MapDTO("orderStatistic", orderStatistic), new MapDTO("incomeStatistic", incomeStatistic)));
        return result;
    }

    public String saveToEs(OrderEntity order) throws IOException {
        Index index = new Index.Builder(order).index(ES_SO_STORE_ORDER_INDEX).type("order").id(order.getOrderId()).build();
        JestResult jestResult = jestClient.execute(index);

        return ((DocumentResult) jestResult).getId();
    }

    private CommonDTO<OrderEntity> getOrderDetailResult(List<OrderEntity> data) {
        CommonDTO<OrderEntity> result = new CommonDTO<>();
        Set<String> goodsIds = new HashSet<>();
        data.forEach(d -> {
            for (String s : d.getGoods().split(",")) {
                goodsIds.add(s.split(":")[0]);
            }
        });
        List<GoodsEntity> goods = goodsRepository.findAllByGoodsIdIn(new ArrayList<>(goodsIds));

        result.setResultList(data);
        result.setExtra(StringUtil.getNewExtraMap(new MapDTO("goods", goods.stream().collect(Collectors.toMap(GoodsEntity::getGoodsId, p -> p)))));
        result.setTotal((long) data.size());
        return result;
    }
}