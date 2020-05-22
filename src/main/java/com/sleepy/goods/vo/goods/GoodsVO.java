package com.sleepy.goods.vo.goods;

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

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("商品数组")
    private List<GoodsNewVO> goods;

    @ApiModelProperty("商品分类")
    private Integer category;

    @ApiModelProperty("商品子分类")
    private Integer subCategory;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品描述")
    private String goodsDesc;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("规格列表")
    private String specList;

    private Integer page;
    private Integer pageSize;
}