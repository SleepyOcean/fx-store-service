package com.sleepy.goods.service;

import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.SettlementDTO;
import com.sleepy.goods.entity.OrderEntity;
import com.sleepy.goods.vo.CartVO;
import com.sleepy.goods.vo.cart.CartSettlementVO;
import com.sleepy.goods.vo.order.OrderNewVO;
import com.sleepy.goods.vo.order.OrderSearchVO;
import com.sleepy.goods.vo.order.OrderStatisticVO;
import com.sleepy.goods.vo.order.UpdateStatusVO;

/**
 * @author Captain1920
 * @create 2020/2/14 20:23
 */
public interface OrderService {
    CommonDTO<OrderEntity> getOrderListByUserId(OrderSearchVO vo);

    CommonDTO<OrderEntity> getOrderByOrderId(String orderId);

    CommonDTO<OrderEntity> saveOrder(OrderNewVO vo) throws Exception;

    CommonDTO<CartDTO> getCartList(String userId);

    CommonDTO<CartDTO> updateCart(CartVO vo) throws Exception;

    CommonDTO<CartDTO> deleteGoodsInCart(CartVO vo);

    CommonDTO<OrderEntity> getOrderListByCond(OrderSearchVO vo);

    CommonDTO<SettlementDTO> settlement(CartSettlementVO vo) throws Exception;

    CommonDTO<OrderEntity> updateOrderStatus(UpdateStatusVO vo);

    CommonDTO<OrderEntity> assignOrder(String status);

    CommonDTO<String> statistic(OrderStatisticVO vo);
}
