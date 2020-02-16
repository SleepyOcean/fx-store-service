package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.service.OrderService;
import com.sleepy.goods.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制器 Controller
 *
 * @author Captain1920
 * @create 2020/2/14 20:00
 */
@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order/get")
    public CommonDTO<String> save(@RequestBody OrderVO vo) {
        return new CommonDTO<>();
    }

}
