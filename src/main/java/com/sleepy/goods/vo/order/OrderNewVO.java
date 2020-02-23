package com.sleepy.goods.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

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

    @NotNull(message = "商品ID集合goodsIds不能为空")
    @ApiModelProperty("商品IDs")
    private List<String> goodsIds;

    @NotEmpty(message = "收货地址信息addressId不能为空")
    @ApiModelProperty("收货地址信息ID")
    private String addressId;

    @ApiModelProperty("期望配送时间")
    private String expectTime;

    @Range(min = 0, max = 1, message = "付款状态payStatus的值为0或1，0未支付，1已支付")
    @ApiModelProperty("付款状态，0未支付，1已支付")
    private Integer payStatus;

    @Range(min = 0, max = 2, message = "付款方式payWay的值为0、1或2，0货到付款，1支付宝，2微信支付")
    @ApiModelProperty("付款方式，0货到付款，1支付宝，2微信支付")
    private Integer payWay;

    @NotNull(message = "配送方式deliveryWay不能为空，配送方式deliveryWay的值为0或1，0商家配送，1自提")
    @Range(min = 0, max = 1, message = "配送方式deliveryWay的值为0或1，0商家配送，1自提")
    @ApiModelProperty("配送方式，0商家配送，1自提")
    private Integer deliveryWay;

    @Range(min = 0, max = 2, message = "订单状态deliveryStatus的值为0、1或2，0已下单，1配送中，2配送完成")
    @ApiModelProperty("订单状态，0接单，1配送中，2配送完成")
    private Integer deliveryStatus;

    @Pattern(regexp = "[0-9]+:[\\u4e00-\\u9fa5]+", message = "配送人信息deliveryManInfo必须符合格式, 格式为 -> 手机号:配送人姓名")
    @ApiModelProperty("配送人信息，手机号:配送人姓名")
    private String deliveryManInfo;

    @Length(max = 255, message = "订单备注长度不能大于255")
    @ApiModelProperty("订单备注")
    private String comment;

    @NotEmpty(message = "结算时间settlementTime不能为空")
    @ApiModelProperty("结算时间")
    private String settlementTime;
}