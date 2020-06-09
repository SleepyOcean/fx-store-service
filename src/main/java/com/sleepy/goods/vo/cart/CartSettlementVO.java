package com.sleepy.goods.vo.cart;

import io.swagger.annotations.ApiModel;
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

    @NotNull(message = "购物车结算商品数据goods不能为空")
    private Map<String, Integer> goodSpecMap;

}