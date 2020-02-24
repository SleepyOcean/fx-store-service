package com.sleepy.goods.dto;

import lombok.Data;

/**
 * 商品价格DTO
 *
 * @author gehoubao
 * @create 2020-02-24 12:35
 **/
@Data
public class SettlementGoodsPriceDTO {
    private String goodsId;
    private Double priceNow;
    private Integer amount;
    private Double totalPrice;
}