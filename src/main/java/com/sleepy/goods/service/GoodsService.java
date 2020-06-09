package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.entity.GoodsSpecEntity;
import com.sleepy.goods.entity.GoodsSpecKeyEntity;
import com.sleepy.goods.entity.GoodsSpecValueEntity;
import com.sleepy.goods.vo.goods.*;

/**
 * 商品服务Service
 *
 * @author gehoubao
 * @create 2020-02-14 20:36
 **/
public interface GoodsService {
    CommonDTO<GoodsEntity> getGoodsList(GoodsVO vo);

    CommonDTO<GoodsEntity> saveGoods(GoodsNewVO vo) throws Exception;

    CommonDTO<GoodsEntity> updateGoods(GoodsVO vo);

    CommonDTO<GoodsSpecEntity> saveSpec(GoodsSpecNewVO vo);

    CommonDTO<GoodsSpecEntity> updateSpec(GoodsSpecVO vo);

    CommonDTO<GoodsSpecEntity> deleteSpec(GoodsSpecVO vo);

    CommonDTO<GoodsSpecKeyEntity> saveSpecKey(GoodsSpecNewKeyVO vo);

    CommonDTO<GoodsSpecKeyEntity> updateSpecKey(GoodsSpecKeyVO vo);

    CommonDTO<GoodsSpecKeyEntity> deleteSpecKey(GoodsSpecKeyVO vo);

    CommonDTO<GoodsSpecValueEntity> saveSpecValue(GoodsSpecNewValueVO vo);

    CommonDTO<GoodsSpecValueEntity> updateSpecValue(GoodsSpecValueVO vo);

    CommonDTO<GoodsSpecValueEntity> deleteSpecValue(GoodsSpecValueVO vo);

    CommonDTO<GoodsSpecEntity> getSpecByGoodsId(String goodsId);
}
