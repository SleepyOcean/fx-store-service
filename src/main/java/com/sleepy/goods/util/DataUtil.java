package com.sleepy.goods.util;

import com.sleepy.goods.common.Constant;
import com.sleepy.goods.dto.order.SingleGoodOrderDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据工具类
 *
 * @author gehoubao
 * @create 2020-05-20 16:13
 **/
public class DataUtil {
    public static String getGoodsStringForOrder(List<SingleGoodOrderDTO> list) {
        List<String> strList = list.stream().map(l -> l.toString()).collect(Collectors.toList());
        return String.join(Constant.COMMA, strList);
    }
}