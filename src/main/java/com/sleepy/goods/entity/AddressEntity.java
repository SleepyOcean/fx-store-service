package com.sleepy.goods.entity;

import com.sleepy.goods.vo.user.AddressNewVO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class AddressEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String addressId;
    @Column(name = "user_id", columnDefinition = "VARCHAR(64) COMMENT '用户id'")
    private String userId;
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

    public AddressEntity() {
    }

    public AddressEntity(AddressNewVO vo) {
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