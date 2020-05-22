package com.sleepy.goods.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 地址新增VO
 *
 * @author gehoubao
 * @create 2020-02-22 13:26
 **/
@Data
@ApiModel("地址新增VO")
public class AddressNewVO {
    @NotEmpty(message = "用户ID不能为空")
    @ApiModelProperty("用户ID")
    private Long userId;

    @NotEmpty(message = "收货人联系方式contact不能为空")
    @ApiModelProperty("收货人联系方式contact")
    private String contact;

    @NotEmpty(message = "收货人姓名contactName不能为空")
    @ApiModelProperty("收货人姓名contactName")
    private String contactName;

    @NotEmpty(message = "地址所在省市县addressProvince不能为空")
    @ApiModelProperty("地址所在省市县addressProvince")
    private String addressProvince;

    @NotEmpty(message = "地址所在市addressCity不能为空")
    @ApiModelProperty("地址所在市addressCity")
    private String addressCity;

    @NotEmpty(message = "地址所在区addressCounty不能为空")
    @ApiModelProperty("地址所在区addressCounty")
    private String addressCounty;

    @NotEmpty(message = "地址所在街道addressStreet不能为空")
    @ApiModelProperty("地址所在街道addressStreet")
    private String addressStreet;

    @NotEmpty(message = "收货人地址contactAddress不能为空")
    @ApiModelProperty("收货人地址contactAddress")
    private String contactAddress;
}