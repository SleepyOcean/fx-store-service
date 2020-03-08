package com.sleepy.goods.entity;

import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.goods.GoodsNewVO;
import com.sleepy.goods.vo.goods.GoodsUpdateVO;
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

    @Column(name = "sub_category", columnDefinition = "TINYINT NOT NULL COMMENT '商品子分类'")
    private Integer subCategory;

    @Column(name = "storage_num", columnDefinition = "INT NOT NULL COMMENT '库存剩余'")
    private Integer storageNum;

    @Column(name = "storage_unit", columnDefinition = "VARCHAR(4) COMMENT '库存单位'")
    private String storageUnit;

    @Column(name = "goods_desc", columnDefinition = "VARCHAR(255) COMMENT '商品描述'")
    private String goodsDesc;

    @Column(name = "img_url", columnDefinition = "VARCHAR(512) NOT NULL COMMENT '图片地址，多个以逗号分隔'")
    private String imgUrl;

    @Column(name = "detail_img_url", columnDefinition = "VARCHAR(512) NOT NULL COMMENT '详情图片地址，多个以逗号分隔'")
    private String detailImgUrl;

    @Column(name = "goods_price_vip", columnDefinition = "DOUBLE(10,2) COMMENT '商品会员价'")
    private Double goodsPriceVip;

    @Column(name = "goods_price_origin", columnDefinition = "DOUBLE(10,2) COMMENT '商品原价'")
    private Double goodsPriceOrigin;

    @Column(name = "goods_price_Now", columnDefinition = "DOUBLE(10,2) NOT NULL COMMENT '商品现价'")
    private Double goodsPriceNow;

    @Column(name = "specification", columnDefinition = "TEXT COMMENT '产品规格，json对象'")
    private String specification;

    @Column(name = "update_time", columnDefinition = "VARCHAR(32) COMMENT '商品信息最新更新时间'")
    private String updateTime;

    public GoodsEntity(GoodsNewVO vo) {
        if(StringUtil.isNotNullOrEmpty(vo.getStorageUnit())){
            this.storageUnit = vo.getStorageUnit();
        } else {
            this.storageUnit = "例";
        }
        this.goodsName = vo.getGoodsName();
        this.category = vo.getCategory();
        this.subCategory = vo.getSubCategory();
        this.storageNum = vo.getStorageNum();
        this.imgUrl = vo.getImgUrl();
        this.detailImgUrl = vo.getDetailImgUrl();
        this.goodsPriceNow = vo.getGoodsPriceNow();
    }

    public GoodsEntity() {
    }

    public void update(GoodsUpdateVO vo) {
        this.goodsName = vo.getGoodsName();
        this.goodsDesc = vo.getGoodsDesc();
        this.category = vo.getCategory();
        this.subCategory = vo.getSubCategory();
        this.imgUrl = vo.getImgUrl();
        this.detailImgUrl = vo.getDetailImgUrl();
        this.goodsPriceVip = vo.getGoodsPriceVip();
        this.goodsPriceOrigin = vo.getGoodsPriceOrigin();
        this.goodsPriceNow = vo.getGoodsPriceNow();
        this.storageNum = vo.getStorageNum();
        this.storageUnit = vo.getStorageUnit();
    }
}