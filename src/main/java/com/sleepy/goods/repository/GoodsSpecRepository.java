package com.sleepy.goods.repository;

import com.sleepy.goods.entity.GoodsSpecEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/

public interface GoodsSpecRepository extends JpaRepository<GoodsSpecEntity, Long> {

    List<GoodsSpecEntity> findAllByIdIn(List<Long> ids);

    List<GoodsSpecEntity> findAllByGoodsId(Long goodsId);

    List<GoodsSpecEntity> findAllByGoodsIdIn(List<Long> goodsIds);
}
