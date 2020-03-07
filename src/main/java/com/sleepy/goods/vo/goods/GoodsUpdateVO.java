package com.sleepy.goods.vo.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品更新VO
 *
 * @author gehoubao
 * @create 2020-03-05 15:29
 **/
@Data
public class GoodsUpdateVO {

    @NotEmpty(message = "商品ID不能为空")
    @ApiModelProperty("商品ID")
    private String goodsId;

    @NotEmpty(message = "商品名称goodsName不能为空")
    @ApiModelProperty("商品名称")
    private String goodsName;

    @NotNull(message = "商品分类category不能为空")
    @ApiModelProperty("商品分类")
    private Integer category;

    @Min(value = 0, message = "库存剩余storageNum不能为空")
    @ApiModelProperty("库存剩余")
    private Integer storageNum;

    @NotEmpty(message = "库存单位storageUnit不能为空")
    @ApiModelProperty("库存单位")
    private String storageUnit;

    @NotEmpty(message = "商品描述不能为空")
    @ApiModelProperty("商品描述")
    private String goodsDesc;

    @NotEmpty(message = "图片地址imgUrl不能为空")
    @ApiModelProperty("图片地址")
    private String imgUrl;

//    @NotEmpty
    @ApiModelProperty("详情图片地址，多个以逗号分隔")
    private String detailImgUrl;

//    @NotNull(message = "商品会员价不能为空")
    @DecimalMin(value = "0")
    @ApiModelProperty("商品会员价")
    private Double goodsPriceVip;

    @NotNull(message = "商品原价不能为空")
    @DecimalMin(value = "0")
    @ApiModelProperty("商品原价")
    private Double goodsPriceOrigin;

    @NotNull(message = "商品现价不能为空")
    @DecimalMin(value = "0")
    @ApiModelProperty("商品现价")
    private Double goodsPriceNow;
}