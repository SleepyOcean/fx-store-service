package com.sleepy.goods.entity;

import com.sleepy.goods.vo.category.CategoryNewVO;
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

    @Column(name = "category_img_url", columnDefinition = "VARCHAR(256) COMMENT '分类图片url'")
    private String categoryImgUrl;

    @Column(name = "sub_category", columnDefinition = "TEXT COMMENT '子分类信息'")
    private String subCategory;

    @Column(name = "deleted", columnDefinition = "TINYINT NOT NULL COMMENT '是否有效， 0-默认，1-已删除'")
    private Integer deleted;

    public CategoryEntity() {
        this.deleted = 0;
    }

    public CategoryEntity(CategoryNewVO category) {
        this();
        this.categoryType = category.getCategoryType();
        this.categoryTypeName = category.getCategoryTypeName();
        this.categoryCode = category.getCategoryCode();
        this.categoryName = category.getCategoryName();
    }
}