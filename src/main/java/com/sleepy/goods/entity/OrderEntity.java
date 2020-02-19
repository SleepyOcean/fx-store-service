package com.sleepy.goods.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Captain1920
 * @create 2020/2/14 14:55
 */
@Data
@Entity
@Table(name = "csg_order")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OrderEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String orderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "goods")
    private String goods;

    @Column(name = "contact")
    private String contact;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_Time")
    private String orderTime;

    @Column(name = "pay_way")
    private String payWay;

    @Column(name = "pay_time")
    private String payTime;

    @Column(name = "delivery_way")
    private String deliveryWay;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "delivery_man")
    private String deliveryMan;
}
