package com.sleepy.goods.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * http请求返回状态码定义
 *
 * @author gehoubao
 * @create 2020-03-05 15:14
 **/
@ApiModel("http请求返回状态码定义")
public class RestfulStatusCode {
    @ApiModelProperty("成功")
    public final static int OK = 200;

    @ApiModelProperty("未接受")
    public final static int NOT_ACCEPTABLE = 406;

    @ApiModelProperty("服务器内部错误")
    public final static int INTERNAL_SERVER_ERROR = 500;
}