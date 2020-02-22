package com.sleepy.goods.entity;

import com.sleepy.goods.vo.goods.GoodsNewVO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商品实体类
 *
 * @author gehoubao
 * @create 2020-02-14 20:39
 **/
@Data
@Entity
@Table(name = "fx_goods")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GoodsEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String goodsId;

    @Column(name = "goods_name", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '商品名称'")
    private String goodsName;

    @Column(name = "category", columnDefinition = "TINYINT NOT NULL COMMENT '商品分类'")
    private Integer category;

    @Column(name = "storage_num", columnDefinition = "INT NOT NULL COMMENT '库存剩余'")
    private Integer storageNum;

    @Column(name = "storage_unit", columnDefinition = "VARCHAR(4) DEFAULT '个' COMMENT '库存单位'")
    private String storageUnit;

    @Column(name = "goods_desc", columnDefinition = "VARCHAR(255) COMMENT '商品描述'")
    private String goodsDesc;

    @Column(name = "imgUrl", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '图片地址'")
    private String imgUrl;

    @Column(name = "goods_price_vip", columnDefinition = "DOUBLE(10,2) COMMENT '商品会员价'")
    private Double goodsPriceVip;

    @Column(name = "goods_price_origin", columnDefinition = "DOUBLE(10,2) COMMENT '商品原价'")
    private Double goodsPriceOrigin;

    @Column(name = "goods_price_now", columnDefinition = "DOUBLE(10,2) NOT NULL COMMENT '商品现价'")
    private Double goodsPriceNow;

    public GoodsEntity(GoodsNewVO vo) {
        this.goodsName = vo.getGoodsName();
        this.category = vo.getCategory();
        this.storageNum = vo.getStorageNum();
        this.storageUnit = vo.getStorageUnit();
        this.imgUrl = vo.getImgUrl();
        this.goodsPriceNow = vo.getGoodsPriceNow();
    }

    public GoodsEntity() {
    }
}