package com.sleepy.goods.vo.goods;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商品规格Value表VO
 *
 * @author gehoubao
 * @create 2020-05-21 13:25
 **/
@Data
public class GoodsSpecValueVO {
    @NotNull(message = "商品规格ValueID(specValueId)不能为空")
    private Long specValueId;

    private Long specKey;

    private String specValue;
}