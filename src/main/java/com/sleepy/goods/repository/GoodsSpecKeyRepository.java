package com.sleepy.goods.repository;

import com.sleepy.goods.entity.GoodsSpecKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品规格KEY表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/

public interface GoodsSpecKeyRepository extends JpaRepository<GoodsSpecKeyEntity, Long> {

}
