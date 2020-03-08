package com.sleepy.goods.vo.order;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 订单统计VO
 *
 * @author gehoubao
 * @create 2020-03-07 14:37
 **/
@Data
public class OrderStatisticVO {

    @NotNull(message = "订单统计时间statisticDaysBefore不能为空")
    @Min(value = 0, message = "订单统计时间范围必须大于0")
    private int statisticDaysBefore;

    @NotEmpty(message = "订单统计类型statisticType不能为空")
    private String statisticType;
}