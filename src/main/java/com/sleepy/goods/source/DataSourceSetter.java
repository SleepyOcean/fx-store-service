package com.sleepy.goods.source;

import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.entity.*;

import java.util.Map;

/**
 * @author ghb
 * @create 2020-05-21 9:58
 **/
public interface DataSourceSetter {

    void saveCart(String userId, Map<String, CartDTO> cart);

    void saveCart(UserEntity user, Map<String, CartDTO> cart);

    GoodsSpecEntity saveGoodSpec(GoodsSpecEntity entity);

    void deleteGoodSpec(String specId);

    GoodsSpecKeyEntity saveGoodSpecKey(GoodsSpecKeyEntity entity);

    void deleteGoodSpecKey(Long specKeyId);

    GoodsSpecValueEntity saveGoodSpecValue(GoodsSpecValueEntity entity);

    void deleteGoodSpecValue(Long specValueId);

    GoodsEntity saveGood(GoodsEntity entity);
}
