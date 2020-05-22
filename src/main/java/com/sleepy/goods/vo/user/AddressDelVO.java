package com.sleepy.goods.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 地址删除VO
 *
 * @author gehoubao
 * @create 2020-02-23 12:05
 **/
@Data
@ApiModel("地址删除VO")
public class AddressDelVO {
    @NotNull(message = "待删除的地址信息ID集合deleteAddressIds不能为空")
    @ApiModelProperty("待删除的地址信息ID集合")
    private List<Long> deleteAddressIds;

    @ApiModelProperty("新的默认地址ID")
    private Long defaultAddressId;

    @NotEmpty(message = "用户userId不能为空")
    @ApiModelProperty("用户ID")
    private Long userId;
}