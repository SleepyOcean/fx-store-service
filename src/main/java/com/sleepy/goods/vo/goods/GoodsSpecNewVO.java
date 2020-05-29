package com.sleepy.goods.vo.goods;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 商品规格新建VO
 *
 * @author gehoubao
 * @create 2020-05-21 13:10
 **/
@Data
public class GoodsSpecNewVO {

    @NotNull(message = "商品ID(goodsId)不能为空")
    private String goodsId;

    private String spec;

    private Double goodsPriceVip;

    private Double goodsPriceOrigin;

    @NotNull(message = "商品现价goodsPriceNow不能为空")
    @DecimalMin(value = "0", message = "商品现价goodsPriceNow需要>=0")
    private Double goodsPriceNow;

    @NotNull(message = "库存(stock)不能为空")
    @Min(value = 0, message = "库存(stock)需要>=0")
    private Integer stock;

    private String saleTag;
}