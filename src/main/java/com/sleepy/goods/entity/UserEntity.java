package com.sleepy.goods.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Captain1920
 * @create 2020/2/14 14:28
 */
@Data
@Entity
@Table(name = "csg_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String userId;

    @Column(name = "open_id")
    @ApiModelProperty("微信用户唯一标识")
    private String openId;

    @Column(name = "user_name")
    @ApiModelProperty("用户名称")
    private String userName;

    @Column(name = "delivery_info")
    @ApiModelProperty("收货信息： 收货人姓名+收货人联系方式+收货地址，多个以逗号分隔")
    private String deliveryInfo;
}
