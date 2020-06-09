package com.sleepy.goods.source.Impl;

import com.alibaba.fastjson.JSON;
import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.entity.*;
import com.sleepy.goods.repository.*;
import com.sleepy.goods.source.DataSourceGetter;
import com.sleepy.goods.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据获取服务实现类
 *
 * @author gehoubao
 * @create 2020-05-20 17:02
 **/
@Component
public class DataSourceGetterImpl implements DataSourceGetter {

    @Autowired
    UserRepository userRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GoodsSpecRepository goodsSpecRepository;
    @Autowired
    GoodsSpecKeyRepository goodsSpecKeyRepository;
    @Autowired
    GoodsSpecValueRepository goodsSpecValueRepository;

    @Override
    public UserEntity getUser(String id) {
        UserEntity entity = userRepository.getOne(id);
        return entity;
    }

    @Override
    public Map<String, CartDTO> getCartMap(UserEntity user) {
        String cartString = user.getCartInfo();
        if (StringUtil.isNullOrEmpty(cartString)) {
            return new HashMap<>();
        }
        Map<String, CartDTO> cartsMap = (Map<String, CartDTO>) JSON.parse(cartString);
        return cartsMap;
    }

    @Override
    public Map<String, CartDTO> getCartMap(String userId) {
        return getCartMap(getUser(userId));
    }

    @Override
    public AddressEntity getAddress(String addressId) {
        AddressEntity address = addressRepository.getOne(addressId);
        return address;
    }

    @Override
    public GoodsSpecEntity getGoodSpec(String specId) {
        GoodsSpecEntity spec = goodsSpecRepository.getOne(specId);
        return spec;
    }

    @Override
    public List<GoodsSpecEntity> getGoodSpecList(String goodId) {
        List<GoodsSpecEntity> specs = goodsSpecRepository.findAllByGoodsId(goodId);
        return specs;
    }

    @Override
    public Map<String, GoodsSpecEntity> getGoodSpecMap(String goodId) {
        List<GoodsSpecEntity> specs = getGoodSpecList(goodId);
        Map<String, GoodsSpecEntity> specMap = specs.stream().collect(Collectors.toMap(GoodsSpecEntity::getId, s -> s));
        return specMap;
    }

    @Override
    public Map<String, List<GoodsSpecEntity>> getGoodSpecListMap(List<String> goodsIds) {
        List<GoodsSpecEntity> specs = goodsSpecRepository.findAllByGoodsIdIn(goodsIds);
        Map<String, List<GoodsSpecEntity>> result = new HashMap<>(goodsIds.size());
        specs.forEach(s -> {
            List<GoodsSpecEntity> item = result.get(s.getGoodsId());
            if (item == null) {
                item = new ArrayList<>();
            }
            item.add(s);
            result.put(s.getGoodsId(), item);
        });
        return result;
    }

    @Override
    public List<GoodsSpecEntity> getGoodSpecList(List<String> specIds) {
        List<GoodsSpecEntity> specs = goodsSpecRepository.findAllByIdIn(specIds);
        return specs;
    }

    @Override
    public Map<String, GoodsSpecEntity> getGoodSpecMap(List<String> specIds) {
        List<GoodsSpecEntity> specs = getGoodSpecList(specIds);
        Map<String, GoodsSpecEntity> specMap = specs.stream().collect(Collectors.toMap(GoodsSpecEntity::getId, s -> s));
        return specMap;
    }

    @Override
    public GoodsSpecKeyEntity getGoodSpecKey(Long specKeyId) {
        GoodsSpecKeyEntity entity = goodsSpecKeyRepository.getOne(specKeyId);
        return entity;
    }

    @Override
    public GoodsSpecValueEntity getGoodSpecValue(Long specValueId) {
        GoodsSpecValueEntity entity = goodsSpecValueRepository.getOne(specValueId);
        return entity;
    }

    @Override
    public GoodsEntity getGoods(String goodsId) {
        GoodsEntity entity = goodsRepository.getOne(goodsId);
        return entity;
    }

}