package com.sleepy.goods.dto.order;

import com.sleepy.goods.common.Constant;
import com.sleepy.goods.util.HPCalcUtil;
import com.sleepy.goods.util.StringUtil;
import lombok.Data;

/**
 * 单个商品订单详情DTO
 *
 * @author gehoubao
 * @create 2020-05-20 16:15
 **/
@Data
public class SingleGoodOrderDTO {
    private String specId;
    private Integer selectedNum;
    private Double goodsPriceNow;
    private String note;

    public SingleGoodOrderDTO(String specId, Integer selectedNum, Double goodsPriceNow, String note) {
        this.specId = specId;
        this.selectedNum = selectedNum;
        this.goodsPriceNow = goodsPriceNow;
        this.note = note;
    }

    @Override
    public String toString() {
        return StringUtil.getSplitString(Constant.PROPERTY_SPLIT_SYMBOL,
                String.valueOf(specId),
                String.valueOf(selectedNum),
                String.valueOf(HPCalcUtil.mul(selectedNum, goodsPriceNow)),
                note);
    }
}