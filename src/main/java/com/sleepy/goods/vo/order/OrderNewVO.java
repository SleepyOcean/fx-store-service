package com.sleepy.goods.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 订单新建VO
 *
 * @author gehoubao
 * @create 2020-02-21 15:10
 **/
@Data
@ApiModel("订单新建VO")
public class OrderNewVO {

    @NotEmpty(message = "用户userId不能为空")
    @ApiModelProperty("用户ID")
    private String userId;

    //    @Pattern(regexp  = "\\w{32}:[1-9][0-9]*",message = "商品ID集合goods不能为空")
    @NotEmpty(message = "商品ID集合goods不能为空，商品id:商品个数:商品价格:备注，多个以逗号分隔")
    @ApiModelProperty("商品ID:商品数量，多个以逗号分隔")
    private String goods;

    @NotEmpty(message = "收货地址信息addressId不能为空")
    @ApiModelProperty("收货地址信息ID")
    private String addressId;

    @NotNull(message = "期望配送时间expectTime不能为空，格式：yyyy-MM-dd,HH:00-HH:00")
    @Pattern(regexp = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))),(0[0-9]|1[0-9]|2[0-3]):00-(0[0-9]|1[0-9]|2[0-3]):00", message = "期望配送时间信息expectTime不能为空且必须符合格式，格式：yyyy-MM-dd,HH:00-HH:00")
    @ApiModelProperty("期望配送时间，格式：yyyy-MM-dd,HH:00-HH:00")
    private String expectTime;

    @NotNull(message = "付款状态payStatus不能为空，付款状态payStatus的值为0或1，0未支付，1已支付")
    @Range(min = 0, max = 1, message = "付款状态payStatus的值为0或1，0未支付，1已支付")
    @ApiModelProperty("付款状态，0未支付，1已支付")
    private Integer payStatus;

    @NotNull(message = "付款方式payWay不能为空，付款方式payWay的值为0、1或2，0货到付款，1支付宝，2微信支付")
    @Range(min = 0, max = 2, message = "付款方式payWay的值为0、1或2，0货到付款，1支付宝，2微信支付")
    @ApiModelProperty("付款方式，0货到付款，1支付宝，2微信支付")
    private Integer payWay;

    @NotNull(message = "配送方式deliveryWay不能为空，配送方式deliveryWay的值为0或1，0商家配送，1自提")
    @Range(min = 0, max = 1, message = "配送方式deliveryWay的值为0或1，0商家配送，1自提")
    @ApiModelProperty("配送方式，0商家配送，1自提")
    private Integer deliveryWay;

    @NotNull(message = "订单状态deliveryStatus不能为空，订单状态deliveryStatus的值为-1、0、1或2，-1未确认，0已下单，1配送中，2配送完成")
    @Range(min = -1, max = 2, message = "订单状态deliveryStatus的值为-1、0、1或2，-1未确认，0已下单，1配送中，2配送完成")
    @ApiModelProperty("订单状态，-1未确认，0接单，1配送中，2配送完成")
    private Integer deliveryStatus;

    @NotEmpty(message = "配送人信息deliveryManInfo不能为空，配送人信息deliveryManInfo必须符合格式, 格式为 -> 手机号:配送人姓名")
    @Pattern(regexp = "[0-9]+:[\\u4e00-\\u9fa5]+", message = "配送人信息deliveryManInfo必须符合格式, 格式为 -> 手机号:配送人姓名")
    @ApiModelProperty("配送人信息，手机号:配送人姓名")
    private String deliveryManInfo;
}