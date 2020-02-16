package com.sleepy.goods.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 公共返回数据集
 *
 * @author Captain
 * @create 2019-04-20 13:28
 */
@Data
public class CommonDTO<T> {
    @ApiModelProperty("单个结果")
    private T result;
    @ApiModelProperty("结果集合")
    private List<T> resultList;
    @ApiModelProperty("额外数据")
    private Map<String, Object> extra;
    @ApiModelProperty("结果总数")
    private Long total;
    @ApiModelProperty("请求状态，200-正常， 500-服务器内部异常")
    private Integer status;
    @ApiModelProperty("错误信息")
    private String message;
    @ApiModelProperty("请求耗时")
    private Double timeout;
}
