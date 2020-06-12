package com.sleepy.goods.vo.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 购物车结算VO
 *
 * @author gehoubao
 * @create 2020-02-23 11:57
 **/
@Data
@ApiModel("购物车结算VO")
public class CartSettlementVO {

    private String addressId;

    @NotEmpty(message = "userId不能为空")
    private String userId;

    @NotNull(message = "购物车结算商品数据goodSpecMap不能为空")
    @ApiModelProperty("购物车结算商品map，key 为商品规格Id，value 为对应规格商品的数量")
    private Map<String, Integer> goodSpecMap;

}