package com.sleepy.goods.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 地址VO
 *
 * @author gehoubao
 * @create 2020-02-22 13:33
 **/
@Data
@ApiModel("地址新增VO")
public class AddressVO {

    @ApiModelProperty("待删除的地址信息ID集合")
    private List<String> deleteAddressIds;

    @ApiModelProperty("地址信息ID")
    private String addressId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("收货人联系方式contact")
    private String contact;

    @ApiModelProperty("收货人姓名contactName")
    private String contactName;

    @ApiModelProperty("收货人地址contactAddress")
    private String contactAddress;
}