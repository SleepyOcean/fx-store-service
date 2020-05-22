package com.sleepy.goods.vo.goods;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 商品规格KEY表新建VO
 *
 * @author gehoubao
 * @create 2020-05-21 13:25
 **/
@Data
public class GoodsSpecNewKeyVO {
    @NotEmpty(message = "规格KeY值(specName)不能为空")
    private String specName;
}