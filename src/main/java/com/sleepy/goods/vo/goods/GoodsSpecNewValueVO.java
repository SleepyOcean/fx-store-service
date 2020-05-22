package com.sleepy.goods.vo.goods;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品规格Value表新建VO
 *
 * @author gehoubao
 * @create 2020-05-21 13:25
 **/
@Data
public class GoodsSpecNewValueVO {
    @NotNull(message = "商品规格ID(specId)不能为空")
    private Long specKey;

    @NotEmpty(message = "规格Value值(specValue)不能为空")
    private String specValue;
}