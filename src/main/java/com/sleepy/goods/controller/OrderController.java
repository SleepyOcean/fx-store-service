package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.SettlementDTO;
import com.sleepy.goods.entity.OrderEntity;
import com.sleepy.goods.service.OrderService;
import com.sleepy.goods.vo.cart.CartSettlementVO;
import com.sleepy.goods.vo.cart.CartVO;
import com.sleepy.goods.vo.order.OrderNewVO;
import com.sleepy.goods.vo.order.OrderSearchVO;
import com.sleepy.goods.vo.order.OrderStatisticVO;
import com.sleepy.goods.vo.order.UpdateStatusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation("获取订单列表")
    @PostMapping("/order/list")
    public CommonDTO<OrderEntity> getOrderList(@RequestBody OrderSearchVO vo) {
        return orderService.getOrderListByCond(vo);
    }

    @ApiOperation("获取指定用户订单列表")
    @GetMapping("/order/getByUserId")
    public CommonDTO<OrderEntity> getOrderByUserId(@RequestParam("userId") String userId,
                                                   @RequestParam("page") Integer page,
                                                   @RequestParam("pageSize") Integer pageSize,
                                                   @RequestParam(value = "deliveryStatus", required = false) Integer deliveryStatus) {
        OrderSearchVO vo = new OrderSearchVO();
        vo.setPage(page);
        vo.setPageSize(pageSize);
        vo.setUserId(userId);
        if (deliveryStatus != null) {
            vo.setDeliveryStatus(deliveryStatus);
        }
        return orderService.getOrderListByUserId(vo);
    }

    @ApiOperation("获取指定用户订单列表")
    @GetMapping("/order/getByOrderId")
    public CommonDTO<OrderEntity> getOrderByOrderId(@RequestParam("orderId") String orderId) {
        return orderService.getOrderByOrderId(orderId);
    }

    @ApiOperation("订单统计")
    @PostMapping("/order/statistic")
    public CommonDTO<String> statistic(@RequestBody @Valid OrderStatisticVO vo, BindingResult bindingResult) throws Exception {
        return orderService.statistic(vo);
    }

    @ApiOperation("更新订单状态")
    @PostMapping("/order/updateStatus")
    public CommonDTO<OrderEntity> updateOrderStatus(@RequestBody @Valid UpdateStatusVO vo, BindingResult bindingResult) {
        return orderService.updateOrderStatus(vo);
    }

    @ApiOperation("分配订单状态")
    @GetMapping("/order/assign")
    public CommonDTO<OrderEntity> assignOrder(@RequestParam("status") String status) {
        return orderService.assignOrder(status);
    }

    @ApiOperation("保存订单")
    @PostMapping("/order/save")
    public CommonDTO<OrderEntity> saveOrder(@RequestBody @Valid OrderNewVO vo, BindingResult bindingResult) throws Exception {
        return orderService.saveOrder(vo);
    }

    @ApiOperation("购物车结算")
    @PostMapping("/cart/settlement")
    public CommonDTO<SettlementDTO> settlement(@RequestBody @Valid CartSettlementVO vo, BindingResult bindingResult) throws Exception {
        return orderService.settlement(vo);
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
