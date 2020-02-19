package com.sleepy.goods.service.impl;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.repository.GoodsRepository;
import com.sleepy.goods.service.GoodsService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现ServiceImpl
 *
 * @author gehoubao
 * @create 2020-02-14 20:37
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public CommonDTO<GoodsEntity> getGoodsList(GoodsVO vo) {
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        List<GoodsEntity> data = goodsRepository.findAll();
        result.setResultList(data);
        result.setTotal((long) data.size());
        return result;
    }

    @Override
    public CommonDTO<GoodsEntity> saveGoodsList(GoodsVO vo) throws Exception {
        if (vo.getGoods() != null && vo.getGoods().size() > 0) {
            vo.getGoods().forEach(goodsVO -> {
                GoodsEntity good = new GoodsEntity();
                good.setGoodsName(goodsVO.getGoodsName());
                good.setGoodsPriceOrigin(goodsVO.getGoodsPriceOrigin());
                good.setGoodsPriceNow(goodsVO.getGoodsPriceNow());
                good.setImgUrl(goodsVO.getImgUrl());
                good.setStorageNum(goodsVO.getStorageNum());
                good.setStorageUnit(goodsVO.getStorageUnit());
                good.setGoodsDesc(goodsVO.getGoodsDesc());
                goodsRepository.save(good);
            });
        } else if (!StringUtil.isNullOrEmpty(vo.getGoodsName())
                && !StringUtil.isNullOrEmpty(vo.getGoodsPriceNow())
                && !StringUtil.isNullOrEmpty(vo.getGoodsPriceNow())
                && !StringUtil.isNullOrEmpty(vo.getStorageNum())
                && !StringUtil.isNullOrEmpty(vo.getImgUrl())) {
            GoodsEntity good = new GoodsEntity();
            good.setGoodsName(vo.getGoodsName());
            good.setGoodsPriceOrigin(StringUtil.isNullOrEmpty(vo.getGoodsPriceOrigin()) ? "" : vo.getGoodsPriceOrigin());
            good.setGoodsPriceNow(vo.getGoodsPriceNow());
            good.setImgUrl(vo.getImgUrl());
            good.setStorageNum(vo.getStorageNum());
            good.setStorageUnit(vo.getStorageUnit());
            good.setGoodsDesc(StringUtil.isNullOrEmpty(vo.getGoodsDesc()) ? "" : vo.getGoodsDesc());
            goodsRepository.save(good);
        } else {
            throw new Exception("参数不合法，请确认参数是否完备");
        }
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<GoodsEntity> searchGoodsList(String goodsName) {
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        List<GoodsEntity> data = goodsRepository.findAllByGoodsNameIsLike("%" + goodsName + "%");
        result.setResultList(data);
        result.setTotal((long) data.size());
        return result;
    }
}