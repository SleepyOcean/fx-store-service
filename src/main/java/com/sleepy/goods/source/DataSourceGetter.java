package com.sleepy.goods.source;

import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author ghb
 * @create 2020-05-20 17:03
 **/
public interface DataSourceGetter {

    /**
     * 通过用户ID获取用户对象
     *
     * @param id
     * @return
     */
    UserEntity getUser(String id);

    /**
     * 通过用户对象获取购物车对象Map
     *
     * @param user
     * @return
     */
    Map<String, CartDTO> getCartMap(UserEntity user);

    /**
     * 通过用户ID获取购物车对象Map
     *
     * @param userId
     * @return
     */
    Map<String, CartDTO> getCartMap(String userId);

    AddressEntity getAddress(String addressId);

    GoodsSpecEntity getGoodSpec(String specId);

    List<GoodsSpecEntity> getGoodSpecList(String goodId);

    Map<String, GoodsSpecEntity> getGoodSpecMap(String goodId);

    Map<String, List<GoodsSpecEntity>> getGoodSpecListMap(List<String> goodsIds);

    List<GoodsSpecEntity> getGoodSpecList(List<String> specIds);

    Map<String, GoodsSpecEntity> getGoodSpecMap(List<String> specIds);

    GoodsSpecKeyEntity getGoodSpecKey(Long specKeyId);

    GoodsSpecValueEntity getGoodSpecValue(Long specValueId);

    GoodsEntity getGoods(String goodsId);
}
