package com.sleepy.goods.entity;

import com.sleepy.goods.vo.user.AddressNewVO;
import lombok.Data;

import javax.persistence.*;

/**
 * 地址实体类
 *
 * @author gehoubao
 * @create 2020-02-21 10:21
 **/
@Data
@Entity
@Table(name = "fx_address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long addressId;
    @Column(name = "user_id", columnDefinition = "INT COMMENT '用户id'")
    private long userId;
    @Column(name = "contact", columnDefinition = "VARCHAR(32) COMMENT '收货人联系方式'")
    private String contact;
    @Column(name = "contact_name", columnDefinition = "VARCHAR(64) COMMENT '收货人姓名'")
    private String contactName;
    @Column(name = "address_province", columnDefinition = "VARCHAR(32) COMMENT '地址所在省'")
    private String addressProvince;
    @Column(name = "address_city", columnDefinition = "VARCHAR(32) COMMENT '地址所在市'")
    private String addressCity;
    @Column(name = "address_county", columnDefinition = "VARCHAR(32) COMMENT '地址所在区'")
    private String addressCounty;
    @Column(name = "address_street", columnDefinition = "VARCHAR(32) COMMENT '地址所在街道'")
    private String addressStreet;
    @Column(name = "contact_address", columnDefinition = "VARCHAR(256) COMMENT '收货人详细地址'")
    private String contactAddress;
    @Column(name = "deleted", columnDefinition = "TINYINT NOT NULL COMMENT '是否有效， 0-默认，1-已删除'")
    private Integer deleted;

    public AddressEntity() {
        this.deleted = 0;
    }

    public AddressEntity(AddressNewVO vo) {
        this();
        this.userId = vo.getUserId();
        this.contact = vo.getContact();
        this.contactName = vo.getContactName();
        this.addressProvince = vo.getAddressProvince();
        this.addressCity = vo.getAddressCity();
        this.addressCounty = vo.getAddressCounty();
        this.addressStreet = vo.getAddressStreet();
        this.contactAddress = vo.getContactAddress();
    }
}