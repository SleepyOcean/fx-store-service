package com.sleepy.goods.dto.admin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 管理员DTO
 *
 * @author gehoubao
 * @create 2020-03-01 14:48
 **/
@Data
@Entity
public class AdminDTO {
    @Id
    @Column(length = 32)
    private String adminId;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "contact")
    private String contact;
}