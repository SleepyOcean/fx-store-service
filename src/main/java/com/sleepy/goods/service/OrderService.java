package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.vo.OrderVO;

/**
 * @author Captain1920
 * @create 2020/2/14 20:23
 */
public interface OrderService {
    CommonDTO<String> getOrderList(OrderVO vo);
}
