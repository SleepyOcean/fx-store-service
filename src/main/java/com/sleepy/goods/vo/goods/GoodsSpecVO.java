package com.sleepy.goods.vo.goods;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商品规格VO
 *
 * @author gehoubao
 * @create 2020-05-21 13:12
 **/
@Data
public class GoodsSpecVO {
    @NotNull(message = "商品规格ID(specId)不能为空")
    private String specId;

    private String spec;

    private Double goodsPriceVip;

    private Double goodsPriceOrigin;

    private Double goodsPriceNow;

    private Integer stock;

    private String saleTag;
}