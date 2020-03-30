package com.sleepy.goods.vo.user;

import lombok.Data;

/**
 * 用户VO
 *
 * @author gehoubao
 * @create 2020-02-15 18:40
 **/
@Data
public class UserVO {
    private String appId;
    private String appSecret;
    private String wxCode;

    private String wxOpenId;

    private String userId;
    private String userName;
    private String deliveryInfo;
    private String cartInfo;
    private String orderList;

    private String addressId;

    private String contact;

    private String contactName;

    private String contactAddress;

    private String merchantInfo;
}