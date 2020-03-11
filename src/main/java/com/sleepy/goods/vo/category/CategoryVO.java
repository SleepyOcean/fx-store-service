package com.sleepy.goods.vo.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分类VO
 *
 * @author gehoubao
 * @create 2020-03-11 19:47
 **/
@Data
public class CategoryVO {
    @ApiModelProperty("分类类型")
    private Integer categoryType;

    @ApiModelProperty("分类类型名称")
    private String categoryTypeName;

    @ApiModelProperty("类型编码")
    private Integer categoryCode;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("分类图片url")
    private String categoryImgUrl;

    @ApiModelProperty("子分类信息")
    private Object subCategoryObject;

    @ApiModelProperty("子分类信息")
    private String subCategory;
}