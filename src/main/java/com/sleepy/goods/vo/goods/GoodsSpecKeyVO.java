package com.sleepy.goods.vo.goods;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商品规格KEY表VO
 *
 * @author gehoubao
 * @create 2020-05-21 13:24
 **/
@Data
public class GoodsSpecKeyVO {

    @NotNull(message = "商品规格Key的ID(specKeyId)不能为空")
    private Long specKeyId;

    private String specName;
}