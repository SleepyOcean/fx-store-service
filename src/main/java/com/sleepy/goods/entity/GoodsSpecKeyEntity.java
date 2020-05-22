package com.sleepy.goods.entity;

import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.goods.GoodsSpecNewKeyVO;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品规格key表
 *
 * @author gehoubao
 * @create 2020-05-20 13:12
 **/
@Data
@Entity
@Table(name = "fx_goods_spec_key")
public class GoodsSpecKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "spec_name", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '规格名称'")
    private String specName;

    @Column(name = "create_time", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '创建时间'")
    private String createTime;

    @Column(name = "update_time", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '更新时间'")
    private String updateTime;

    @Column(name = "deleted", columnDefinition = "TINYINT NOT NULL COMMENT '是否有效， 0-默认，1-已删除'")
    private Integer deleted;

    public GoodsSpecKeyEntity() {
        this.deleted = 0;
    }

    public GoodsSpecKeyEntity(GoodsSpecNewKeyVO vo) {
        this();
        this.specName = vo.getSpecName();
        this.createTime = StringUtil.currentTimeStr();
    }
}