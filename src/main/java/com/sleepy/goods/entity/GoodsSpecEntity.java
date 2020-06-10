package com.sleepy.goods.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.goods.GoodsSpecNewVO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商品规格表
 *
 * @author gehoubao
 * @create 2020-05-20 13:13
 **/
@Data
@Entity
@Table(name = "fx_goods_spec")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class GoodsSpecEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String id;

    @Column(name = "goods_id", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '商品id'")
    private String goodsId;

    @Column(name = "spec", columnDefinition = "VARCHAR(256) COMMENT '规格'")
    private String spec;

    @Column(name = "price_vip", columnDefinition = "DOUBLE(10,2) COMMENT '商品会员价'")
    private Double goodsPriceVip;

    @Column(name = "price_origin", columnDefinition = "DOUBLE(10,2) COMMENT '商品原价'")
    private Double goodsPriceOrigin;

    @Column(name = "price_Now", columnDefinition = "DOUBLE(10,2) NOT NULL COMMENT '商品现价'")
    private Double goodsPriceNow;

    @Column(name = "stock", columnDefinition = "INT NOT NULL COMMENT '商品库存'")
    private Integer stock;

    @Column(name = "sale_tag", columnDefinition = "VARCHAR(128) COMMENT '销售标签，多个以逗号分隔'")
    private String saleTag;

    @Column(name = "create_time", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '创建时间'")
    private String createTime;

    @Column(name = "update_time", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '更新时间'")
    private String updateTime;

    @Column(name = "deleted", columnDefinition = "TINYINT NOT NULL COMMENT '是否有效， 0-默认，1-已删除'")
    private Integer deleted;

    public GoodsSpecEntity() {
        this.deleted = 0;
    }

    public GoodsSpecEntity(GoodsSpecNewVO vo) {
        this();
        this.goodsId = vo.getGoodsId();
        this.stock = vo.getStock();
        this.goodsPriceNow = vo.getGoodsPriceNow();
        if (StringUtil.isNotNullOrEmpty(vo.getSpec())) {
            this.spec = vo.getSpec();
        }
        if (StringUtil.isNotNullOrEmpty(vo.getGoodsPriceOrigin())) {
            this.goodsPriceOrigin = vo.getGoodsPriceOrigin();
        }
        if (StringUtil.isNotNullOrEmpty(vo.getGoodsPriceVip())) {
            this.goodsPriceVip = vo.getGoodsPriceVip();
        }
        if (StringUtil.isNotNullOrEmpty(vo.getSaleTag())) {
            this.saleTag = vo.getSaleTag();
        }
        this.createTime = StringUtil.currentTimeStr();
    }
}