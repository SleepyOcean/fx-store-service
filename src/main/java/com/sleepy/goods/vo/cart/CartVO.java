package com.sleepy.goods.vo.cart;

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
    @ApiModelProperty("具体规格商品ID")
    private Long specId;
    @ApiModelProperty("数量变化值")
    private Integer valueChange;
    @ApiModelProperty("批量删除的具体规格商品ID数组")
    private List<String> specIdsDeleted;
}