package com.sleepy.goods.service;

import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.OrderEntity;
import com.sleepy.goods.vo.CartVO;
import com.sleepy.goods.vo.OrderVO;

/**
 * @author Captain1920
 * @create 2020/2/14 20:23
 */
public interface OrderService {
    CommonDTO<OrderEntity> getOrderList(String userId);

    CommonDTO<OrderEntity> saveOrder(OrderVO vo) throws Exception;

    CommonDTO<CartDTO> getCartList(String userId);

    CommonDTO<CartDTO> updateCart(CartVO vo) throws Exception;

    CommonDTO<CartDTO> deleteGoodsInCart(CartVO vo);

    CommonDTO<OrderEntity> getOrderListByCond(OrderVO vo);
}
