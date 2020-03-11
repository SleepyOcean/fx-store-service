package com.sleepy.goods.vo.category;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分类新建VO
 *
 * @author gehoubao
 * @create 2020-03-01 21:57
 **/
@Data
@ApiModel("分类新建VO")
public class CategoryUpdateVO {

    @NotNull
    @ApiModelProperty("分类id")
    private String categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("分类图片url")
    private String categoryImgUrl;

    @ApiModelProperty("子分类信息")
    private String subCategory;
}