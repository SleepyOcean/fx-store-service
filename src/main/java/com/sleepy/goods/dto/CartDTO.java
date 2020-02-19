package com.sleepy.goods.dto;

import com.sleepy.goods.common.Constant;
import lombok.Data;

/**
 * 购物车DTO
 *
 * @author gehoubao
 * @create 2020-02-16 14:23
 **/
@Data
public class CartDTO {

    private String goodsId;

    private String selectedNum;

    @Override
    public String toString() {
        return goodsId + Constant.PROPERTY_SPLIT_SYMBOL + selectedNum;
    }
}