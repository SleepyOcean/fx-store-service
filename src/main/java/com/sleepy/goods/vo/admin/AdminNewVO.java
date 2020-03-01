package com.sleepy.goods.vo.admin;

import lombok.Data;

/**
 * 管理员VO
 *
 * @author gehoubao
 * @create 2020-03-01 14:47
 **/
@Data
public class AdminNewVO {
    private String appId;
    private String appSecret;
    private String wxCode;

    private String wxOpenId;

    private String adminId;
    private String adminName;
    private String deliveryInfo;
    private String cartInfo;
    private String orderList;

    private String addressId;

    private String contact;

    private String contactName;

    private String contactAddress;
}