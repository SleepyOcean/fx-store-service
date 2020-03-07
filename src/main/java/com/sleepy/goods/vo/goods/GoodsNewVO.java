package com.sleepy.goods.vo.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品新建VO
 *
 * @author gehoubao
 * @create 2020-02-21 15:38
 **/
@Data
@ApiModel("商品新建VO")
public class GoodsNewVO {

    @NotEmpty(message = "商品名称goodsName不能为空")
    @ApiModelProperty("商品名称")
    private String goodsName;

    @NotNull(message = "商品分类category不能为空")
    @ApiModelProperty("商品分类")
    private Integer category;

    @Min(value = 0, message = "库存剩余storageNum不能为空")
    @ApiModelProperty("库存剩余")
    private Integer storageNum;

    @ApiModelProperty("库存单位")
    private String storageUnit;

    @ApiModelProperty("商品描述")
    private String goodsDesc;

    @NotEmpty(message = "图片地址imgUrl不能为空")
    @ApiModelProperty("图片地址")
    private String imgUrl;

    @NotEmpty(message = "详情图片地址imgUrl不能为空")
    @ApiModelProperty("详情图片地址")
    private String detailImgUrl;

    @ApiModelProperty("商品会员价")
    private Double goodsPriceVip;

    @ApiModelProperty("商品原价")
    private Double goodsPriceOrigin;

    @DecimalMin(value = "0", message = "商品现价goodsPriceNow不能为空")
    @ApiModelProperty("商品现价")
    private Double goodsPriceNow;
}