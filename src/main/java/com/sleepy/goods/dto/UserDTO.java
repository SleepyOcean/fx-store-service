package com.sleepy.goods.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Captain1920
 * @create 2020/2/14 14:28
 */
@Data
@Entity
public class UserDTO {
    @Id
    @Column(length = 64)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "delivery_info")
    private String deliveryInfo;

    @Column(name = "cart_info", columnDefinition = "text")
    private String cartInfo;

    @Column(name = "order_list", columnDefinition = "text")
    private String orderList;
}
