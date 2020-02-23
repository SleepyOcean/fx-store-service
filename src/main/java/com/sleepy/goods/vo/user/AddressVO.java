package com.sleepy.goods.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 地址VO
 *
 * @author gehoubao
 * @create 2020-02-22 13:33
 **/
@Data
@ApiModel("地址VO")
public class AddressVO {

    @ApiModelProperty("地址信息ID")
    @NotEmpty(message = "addressId不能为空")
    private String addressId;

    @ApiModelProperty("用户ID")
    @NotEmpty(message = "userId不能为空")
    private String userId;

    @ApiModelProperty("收货人联系方式contact")
    private String contact;

    @ApiModelProperty("收货人姓名contactName")
    private String contactName;

    @ApiModelProperty("收货人地址contactAddress")
    private String contactAddress;
}