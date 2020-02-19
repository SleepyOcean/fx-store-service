package com.sleepy.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 购物车VO
 *
 * @author gehoubao
 * @create 2020-02-16 14:42
 **/
@Data
@ApiModel("购物车接口参数")
public class CartVO {
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("商品ID")
    private String goodsId;
    @ApiModelProperty("数量变化值")
    private Integer valueChange;
    @ApiModelProperty("批量删除的商品ID数组")
    private List<String> goodsIdsDeleted;
}