package com.sleepy.goods.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商品分类表
 *
 * @author gehoubao
 * @create 2020-03-01 21:39
 **/
@Data
@Entity
@Table(name = "fx_category")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CategoryEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String categoryId;

    @Column(name = "category_type", columnDefinition = "TINYINT NOT NULL COMMENT '分类类型'")
    private Integer categoryType;

    @Column(name = "category_type_name", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '分类类型名称'")
    private String categoryTypeName;

    @Column(name = "category_code", columnDefinition = "TINYINT NOT NULL COMMENT '类型编码'")
    private Integer categoryCode;

    @Column(name = "category_name", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '分类名称'")
    private String categoryName;
}