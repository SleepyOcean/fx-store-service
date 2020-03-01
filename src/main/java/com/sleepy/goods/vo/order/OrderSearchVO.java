package com.sleepy.goods.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单查询VO
 *
 * @author gehoubao
 * @create 2020-03-01 12:58
 **/
@Data
@ApiModel("订单查询VO")
public class OrderSearchVO {

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("订单ID")
    private String orderId;

    @ApiModelProperty("订单状态，0待配送，1配送中，2配送完成")
    private Integer deliveryStatus;

    @ApiModelProperty("分页参数，表示查询第几页")
    private int page;

    @ApiModelProperty("分页参数，表示每页返回数据量")
    private int pageSize;
}