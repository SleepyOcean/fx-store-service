package com.sleepy.goods.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 分类新建VO
 *
 * @author gehoubao
 * @create 2020-03-01 21:57
 **/
@Data
@ApiModel("分类新建VO")
public class CategoryNewVO {

    @NotNull
    @ApiModelProperty("分类类型")
    private Integer categoryType;

    @NotEmpty
    @ApiModelProperty("分类类型名称")
    private String categoryTypeName;

    @NotNull
    @ApiModelProperty("类型编码")
    private Integer categoryCode;

    @NotEmpty
    @ApiModelProperty("分类名称")
    private String categoryName;
}