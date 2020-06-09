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

    private String goodsSpecId;

    private Integer selectedNum;

    public CartDTO(String specId) {
        this.goodsSpecId = specId;
        this.selectedNum = 0;
    }

    @Override
    public String toString() {
        return goodsSpecId + Constant.PROPERTY_SPLIT_SYMBOL + selectedNum;
    }
}