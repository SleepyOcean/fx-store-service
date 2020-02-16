package com.sleepy.goods.service.impl;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.service.OrderService;
import com.sleepy.goods.vo.OrderVO;
import org.springframework.stereotype.Service;

/**
 * 订单 服务
 *
 * @author gehoubao
 * @create 2020-02-14 20:26
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public CommonDTO<String> getOrderList(OrderVO vo) {
        return null;
    }
}