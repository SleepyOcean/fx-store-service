package com.sleepy.goods.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 购物车商品结算DTO
 *
 * @author gehoubao
 * @create 2020-02-23 13:42
 **/
@Data
@ApiModel("购物车商品结算DTO")
public class SettlementDTO {

    private Double totalPrice;

    private Double goodsTotalPrice;

    private Double deliveryPrice;

    private Double couponPrice;
}