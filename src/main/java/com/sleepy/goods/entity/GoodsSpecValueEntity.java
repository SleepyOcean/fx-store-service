package com.sleepy.goods.entity;

import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.goods.GoodsSpecNewValueVO;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品规格value表
 *
 * @author gehoubao
 * @create 2020-05-20 13:12
 **/
@Data
@Entity
@Table(name = "fx_goods_spec_value")
public class GoodsSpecValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "spec_key")
    private Long specKey;

    @Column(name = "spec_value", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '规格属性值'")
    private String specValue;

    @Column(name = "create_time", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '创建时间'")
    private String createTime;

    @Column(name = "update_time", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '更新时间'")
    private String updateTime;

    @Column(name = "deleted", columnDefinition = "TINYINT NOT NULL COMMENT '是否有效， 0-默认，1-已删除'")
    private Integer deleted;

    public GoodsSpecValueEntity() {
        this.deleted = 0;
    }

    public GoodsSpecValueEntity(GoodsSpecNewValueVO vo) {
        this();
        this.specKey = vo.getSpecKey();
        this.specValue = vo.getSpecValue();
        this.createTime = StringUtil.currentTimeStr();
    }
}