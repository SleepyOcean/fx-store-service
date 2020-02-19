package com.sleepy.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单 vo
 *
 * @author Captain1920
 * @create 2020/2/14 20:13
 */
@Data
@ApiModel("订单参数")
public class OrderVO {

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("商品ID集合+商品数量，多个以逗号分隔")
    private String goods;

    @ApiModelProperty("联系方式")
    private String contact;

    @ApiModelProperty("收获地址")
    private String deliveryAddress;

    @ApiModelProperty("下单时间")
    private String orderTime;

    @ApiModelProperty("付款方式")
    private String payWay;

    @ApiModelProperty("付款时间")
    private String payTime;

    @ApiModelProperty("配送方式")
    private String deliveryWay;

    @ApiModelProperty("配送状态, -1：未配送，0：配送中，1：配送完成")
    private String deliveryStatus;

    @ApiModelProperty("配送人")
    private String deliveryMan;
}
