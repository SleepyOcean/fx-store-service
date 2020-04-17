package com.sleepy.goods.dto;

import com.alibaba.fastjson.JSON;

/**
 * 商家信息DTO
 *
 * @author gehoubao
 * @create 2020-04-17 22:03
 **/
public class MerchantInfoDTO {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}