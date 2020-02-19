package com.sleepy.goods.entity;

import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "csg_goods")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GoodsEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String goodsId;

    @ApiModelProperty("商品名称")
    @Column(name = "goods_name")
    private String goodsName;

    @ApiModelProperty("库存剩余")
    @Column(name = "storage_num")
    private String storageNum;

    @ApiModelProperty("库存单位")
    @Column(name = "storage_unit")
    private String storageUnit;

    @ApiModelProperty("商品描述")
    @Column(name = "goods_desc")
    private String goodsDesc;

    @ApiModelProperty("图片地址")
    @Column(name = "imgUrl")
    private String imgUrl;

    @ApiModelProperty("商品原价")
    @Column(name = "goods_price_origin")
    private String goodsPriceOrigin;

    @ApiModelProperty("商品现价")
    @Column(name = "goods_price_now")
    private String goodsPriceNow;
}