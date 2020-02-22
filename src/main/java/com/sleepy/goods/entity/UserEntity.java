package com.sleepy.goods.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Captain1920
 * @create 2020/2/14 14:28
 */
@Data
@Entity
@Table(name = "fx_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String userId;

    @Column(name = "open_id", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '微信用户唯一标识'")
    private String openId;

    @Column(name = "user_name", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '用户名称'")
    private String userName;

    @Column(name = "cart_info", columnDefinition = "text COMMENT '购物车信息： 商品id:商品数量，多个以逗号分隔'")
    private String cartInfo;
}
