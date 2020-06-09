package com.sleepy.goods.source.Impl;

import com.alibaba.fastjson.JSON;
import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.entity.*;
import com.sleepy.goods.repository.*;
import com.sleepy.goods.source.DataSourceSetter;
import com.sleepy.goods.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 数据存储实现类
 *
 * @author gehoubao
 * @create 2020-05-21 10:00
 **/
@Component
public class DataSourceSetterImpl implements DataSourceSetter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsSpecRepository goodsSpecRepository;
    @Autowired
    GoodsSpecKeyRepository goodsSpecKeyRepository;
    @Autowired
    GoodsSpecValueRepository goodsSpecValueRepository;

    @Override
    public void saveCart(String userId, Map<String, CartDTO> cart) {
        UserEntity user = userRepository.getOne(userId);
        saveCart(user, cart);
    }

    @Override
    public void saveCart(UserEntity user, Map<String, CartDTO> cart) {
        user.setCartInfo(JSON.toJSONString(cart));
        userRepository.saveAndFlush(user);
    }

    @Override
    public GoodsSpecEntity saveGoodSpec(GoodsSpecEntity entity) {
        entity.setUpdateTime(StringUtil.currentTimeStr());
        GoodsEntity goods = goodsRepository.getOne(entity.getGoodsId());
        if (null == goods.getGoodsMinPrice() || goods.getGoodsMinPrice() > entity.getGoodsPriceNow()) {
            goods.setGoodsMinPrice(entity.getGoodsPriceNow());
            goodsRepository.saveAndFlush(goods);
        }
        return goodsSpecRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteGoodSpec(String specId) {
        goodsSpecRepository.deleteById(specId);
    }

    @Override
    public GoodsSpecKeyEntity saveGoodSpecKey(GoodsSpecKeyEntity entity) {
        entity.setUpdateTime(StringUtil.currentTimeStr());
        return goodsSpecKeyRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteGoodSpecKey(Long specKeyId) {
        goodsSpecKeyRepository.deleteById(specKeyId);
    }

    @Override
    public GoodsSpecValueEntity saveGoodSpecValue(GoodsSpecValueEntity entity) {
        entity.setUpdateTime(StringUtil.currentTimeStr());
        return goodsSpecValueRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteGoodSpecValue(Long specValueId) {
        goodsSpecValueRepository.deleteById(specValueId);
    }

    @Override
    public GoodsEntity saveGood(GoodsEntity entity) {
        entity.setUpdateTime(StringUtil.currentTimeStr());
        return goodsRepository.saveAndFlush(entity);
    }
}