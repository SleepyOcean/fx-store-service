package com.sleepy.goods.dto;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商家信息DTO
 *
 * @author gehoubao
 * @create 2020-04-17 22:03
 **/
@Data
@ApiModel("商家信息DTO")
public class MerchantInfoDTO {
    @ApiModelProperty("门店牌匾")
    private String merchantName;
    @ApiModelProperty("经营类型")
    private String businessScope;
    @ApiModelProperty("收货人")
    private String consignee;
    @ApiModelProperty("收货时间")
    private String deliveryPeriod;
    @ApiModelProperty("收货地址")
    private String deliveryAddress;
    @ApiModelProperty("详细地址")
    private String detailAddress;
    @ApiModelProperty("门店照片")
    private String shopPic;
    @ApiModelProperty("营业执照")
    private String businessLicense;
    @ApiModelProperty("审核状态，0-未审核，1-审核通过")
    private Integer checkStatus;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}