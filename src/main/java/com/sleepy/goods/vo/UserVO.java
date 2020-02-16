package com.sleepy.goods.vo;

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

    private String userId;
    private String userName;
    private String deliveryInfo;
}