package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.OrderEntity;
import com.sleepy.goods.service.OrderService;
import com.sleepy.goods.vo.CartVO;
import com.sleepy.goods.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单控制器 Controller
 *
 * @author Captain1920
 * @create 2020/2/14 20:00
 */
@RestController
@CrossOrigin
@Api(value = "订单和购物车操作接口")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation("获取所有未完成的订单列表")
    @PostMapping("/order/list")
    public CommonDTO<OrderEntity> getOrderList(@RequestBody OrderVO vo) {
        return orderService.getOrderListByCond(vo);
    }

    @ApiOperation("获取指定用户订单列表")
    @GetMapping("/order/get")
    public CommonDTO<OrderEntity> getOrder(@RequestParam("userId") String userId) {
        return orderService.getOrderList(userId);
    }

    @ApiOperation("保存订单")
    @PostMapping("/order/save")
    public CommonDTO<OrderEntity> saveOrder(@RequestBody OrderVO vo) throws Exception {
        return orderService.saveOrder(vo);
    }

    @ApiOperation("获取指定用户购物车列表")
    @GetMapping("/cart/get")
    public CommonDTO<CartDTO> getCart(@RequestParam("userId") String userId) {
        return orderService.getCartList(userId);
    }

    @ApiOperation("删除指定用户购物车列表中的商品")
    @PostMapping("/cart/delete")
    public CommonDTO<CartDTO> deleteGoodsInCart(@RequestBody CartVO vo) {
        return orderService.deleteGoodsInCart(vo);
    }

    @ApiOperation("保存")
    @PostMapping("/cart/update")
    public CommonDTO<CartDTO> updateCart(@RequestBody CartVO vo) throws Exception {
        return orderService.updateCart(vo);
    }
}
