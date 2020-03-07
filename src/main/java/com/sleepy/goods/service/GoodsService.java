package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.vo.GoodsVO;
import com.sleepy.goods.vo.goods.GoodsNewVO;
import com.sleepy.goods.vo.goods.GoodsUpdateVO;

/**
 * 商品服务Service
 *
 * @author gehoubao
 * @create 2020-02-14 20:36
 **/
public interface GoodsService {
    CommonDTO<GoodsEntity> getGoodsList(GoodsVO vo);

    CommonDTO<GoodsEntity> saveGoods(GoodsNewVO vo) throws Exception;

    CommonDTO<GoodsEntity> searchGoodsList(GoodsVO vo);

    CommonDTO<GoodsEntity> getByCategory(GoodsVO vo);

    CommonDTO<GoodsEntity> updateGoods(GoodsUpdateVO vo);
}
