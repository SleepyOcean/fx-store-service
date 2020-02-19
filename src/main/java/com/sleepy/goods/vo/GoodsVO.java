package com.sleepy.goods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品VO
 *
 * @author gehoubao
 * @create 2020-02-14 20:40
 **/
@Data
public class GoodsVO {

    @ApiModelProperty("商品数组")
    private List<GoodsVO> goods;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("库存剩余")
    private String storageNum;

    @ApiModelProperty("库存单位")
    private String storageUnit;

    @ApiModelProperty("商品描述")
    private String goodsDesc;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("商品原价")
    private String goodsPriceOrigin;

    @ApiModelProperty("商品现价")
    private String goodsPriceNow;
}