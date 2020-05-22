package com.sleepy.goods.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Captain1920
 * @create 2020/2/14 14:28
 */
@Data
@Entity
@Table(name = "fx_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long userId;

    @Column(name = "open_id", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '微信用户唯一标识'")
    private String openId;

    @Column(name = "user_name", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '用户名称'")
    private String userName;

    @Column(name = "contact", columnDefinition = "VARCHAR(32) COMMENT '手机号码'")
    private String contact;

    @Column(name = "cart_info", columnDefinition = "TEXT COMMENT '购物车信息：{goodSpecId: {goodSpecId, selectedNum} ...}'")
    private String cartInfo;

    @Column(name = "default_address_id", columnDefinition = "INT COMMENT '默认地址id'")
    private long defaultAddressId;

    @Column(name = "merchant_info", columnDefinition = "VARCHAR(1024) COMMENT '商家信息'")
    private String merchantInfo;

    @Column(name = "deleted", columnDefinition = "TINYINT NOT NULL COMMENT '是否有效， 0-默认，1-已删除'")
    private Integer deleted;

    public UserEntity() {
        this.deleted = 0;
    }

}
