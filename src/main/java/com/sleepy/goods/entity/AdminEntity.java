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
@Table(name = "fx_admin")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class AdminEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String adminId;

    @Column(name = "open_id", columnDefinition = "VARCHAR(128) NOT NULL COMMENT '微信用户唯一标识'")
    private String openId;

    @Column(name = "admin_name", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '用户名称'")
    private String adminName;

    @Column(name = "admin_level", columnDefinition = "TINYINT COMMENT '管理员等级，1超级管理员，2普通管理员，3配送员'")
    private Integer adminLevel;

    @Column(name = "contact", columnDefinition = "VARCHAR(32) COMMENT '联系方式'")
    private String contact;

    @Column(name = "deleted", columnDefinition = "TINYINT NOT NULL COMMENT '是否有效， 0-默认，1-已删除'")
    private Integer deleted;

    public AdminEntity() {
        this.deleted = 0;
    }
}
