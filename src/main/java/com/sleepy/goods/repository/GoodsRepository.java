package com.sleepy.goods.repository;

import com.sleepy.goods.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/

public interface GoodsRepository extends JpaRepository<GoodsEntity, String> {
    List<GoodsEntity> findAllByGoodsNameIsLike(String goodsName);

    List<GoodsEntity> findAllByGoodsIdIn(List<String> ids);
}
