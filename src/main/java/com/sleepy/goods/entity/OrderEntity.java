package com.sleepy.goods.entity;

import com.sleepy.goods.vo.order.OrderNewVO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Captain1920
 * @create 2020/2/14 14:55
 */
@Data
@Entity
@Table(name = "fx_order")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OrderEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String orderId;

    @Column(name = "user_id", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '用户id'")
    private String userId;

    @Column(name = "goods", columnDefinition = "TEXT NOT NULL COMMENT '订单商品信息数组字符串（不同商品以逗号分隔），格式:  商品id:商品个数:商品价格:备注 '")
    private String goods;

    @Column(name = "goods_total_price", columnDefinition = "DOUBLE(10,2) NOT NULL COMMENT '商品金额'")
    private Double goodsTotalPrice;

    @Column(name = "coupon_price", columnDefinition = "DOUBLE(10,2) DEFAULT 0 COMMENT '优惠券价值'")
    private Double couponPrice = 0d;

    @Column(name = "delivery_price", columnDefinition = "DOUBLE(10,2) DEFAULT 0 COMMENT '配送费'")
    private Double deliveryPrice = 0d;

    @Column(name = "total_price", columnDefinition = "DOUBLE(10,2) NOT NULL COMMENT '实付款 = 商品金额 + 配送费 - 优惠券'")
    private Double totalPrice;

    @Column(name = "contact", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '收货人联系方式'")
    private String contact;

    @Column(name = "contact_name", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '收货人姓名'")
    private String contactName;

    @Column(name = "contact_address", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '收货人详细地址'")
    private String contactAddress;

    @Column(name = "order_Time", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '下单时间'")
    private String orderTime;

    @Column(name = "expect_time", columnDefinition = "VARCHAR(24) DEFAULT '尽快送达' COMMENT '期望配送时间，yyyy-MM-dd,HH:00-HH:00'")
    private String expectTime = "尽快送达";

    @Column(name = "done_time", columnDefinition = "VARCHAR(16) COMMENT '配送完成时间'")
    private String doneTime;

    @Column(name = "pay_status", columnDefinition = "TINYINT DEFAULT 0 COMMENT '付款状态，0未支付，1已支付'")
    private Integer payStatus = 0;

    @Column(name = "pay_way", columnDefinition = "TINYINT NOT NULL COMMENT '付款方式，0货到付款，1支付宝，2微信支付'")
    private Integer payWay;

    @Column(name = "pay_time", columnDefinition = "VARCHAR(16) COMMENT '付款时间'")
    private String payTime;

    @Column(name = "delivery_way", columnDefinition = "TINYINT NOT NULL COMMENT '配送方式，0商家配送，1自提'")
    private Integer deliveryWay;

    @Column(name = "delivery_status", columnDefinition = "TINYINT DEFAULT 0 COMMENT '订单配送状态，0待配送，1配送中，2配送完成，3退款中'")
    private Integer deliveryStatus = 0;

    @Column(name = "delivery_man_info", columnDefinition = "VARCHAR(64) COMMENT '配送人信息，手机号:配送人姓名'")
    private String deliveryManInfo;

    @Column(name = "status", columnDefinition = "TINYINT COMMENT '订单状态，0订单正常，1订单取消，2订单删除'")
    private Integer status;

    @Column(name = "comment", columnDefinition = "VARCHAR(255) COMMENT '订单备注'")
    private String comment;

    public OrderEntity() {
    }

    public OrderEntity(OrderNewVO vo, AddressEntity entity) {
        this.userId = vo.getUserId();
        this.contactAddress = entity.getContactAddress();
        this.contactName = entity.getContactName();
        this.contact = entity.getContact();
        this.payWay = vo.getPayWay();
        this.deliveryWay = vo.getDeliveryWay();
    }
}
