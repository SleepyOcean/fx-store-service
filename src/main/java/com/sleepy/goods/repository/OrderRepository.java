package com.sleepy.goods.repository;

import com.sleepy.goods.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    List<OrderEntity> findByUserId(String userId);

    List<OrderEntity> findAllByDeliveryStatusOrderByOrderTimeDesc(Integer deliveryStatus);

    List<OrderEntity> findAllByOrderByOrderTimeDesc();
}
