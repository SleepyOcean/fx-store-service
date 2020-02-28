package com.sleepy.goods.vo;

import lombok.Data;

/**
 * 订单 vo
 *
 * @author Captain1920
 * @create 2020/2/14 20:13
 */
@Data
public class OrderVO {

    private String orderId;

    private String userId;

    private String goods;

    private String addressId;

    private String orderTime;

    private String payWay;

    private String payTime;

    private String deliveryWay;

    private Integer deliveryStatus;

    private String deliveryMan;

    private int page;
    private int pageSize;
}
