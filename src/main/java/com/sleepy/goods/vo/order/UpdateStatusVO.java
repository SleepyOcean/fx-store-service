package com.sleepy.goods.vo.order;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 更新订单状态VO
 *
 * @author gehoubao
 * @create 2020-02-26 22:14
 **/
@Data
public class UpdateStatusVO {
    @NotEmpty(message = "管理员密钥adminCode不能为空")
    private String adminCode;

    @NotNull(message = "待更新订单状态status不能为空")
    @Range(min = 0, max = 2, message = "订单状态取值，0待配送，1配送中或2配送完成")
    private Integer status;

    @NotEmpty(message = "待更新订单orderId不能为空")
    private String orderId;
}