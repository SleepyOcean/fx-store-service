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
    UserEntity getUser(long id);

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
    Map<Long, CartDTO> getCartMap(UserEntity user);

    /**
     * 通过用户ID获取购物车对象Map
     *
     * @param userId
     * @return
     */
    Map<Long, CartDTO> getCartMap(long userId);

    AddressEntity getAddress(long addressId);

    AddressEntity getAddress(String addressId);

    GoodsSpecEntity getGoodSpec(long specId);

    List<GoodsSpecEntity> getGoodSpecList(long goodId);

    Map<Long, GoodsSpecEntity> getGoodSpecMap(long goodId);

    Map<Long, List<GoodsSpecEntity>> getGoodSpecListMap(List<Long> goodsIds);

    List<GoodsSpecEntity> getGoodSpecList(List<Long> specIds);

    Map<Long, GoodsSpecEntity> getGoodSpecMap(List<Long> specIds);

    GoodsSpecKeyEntity getGoodSpecKey(Long specKeyId);

    GoodsSpecValueEntity getGoodSpecValue(Long specValueId);

    GoodsEntity getGoods(Long goodsId);
}
