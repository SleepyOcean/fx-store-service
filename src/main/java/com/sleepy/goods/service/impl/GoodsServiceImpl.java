package com.sleepy.goods.service.impl;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.repository.GoodsRepository;
import com.sleepy.goods.service.GoodsService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.GoodsVO;
import com.sleepy.goods.vo.goods.GoodsNewVO;
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
    public CommonDTO<GoodsEntity> saveGoodsList(GoodsNewVO vo) throws Exception {
        GoodsEntity good = new GoodsEntity(vo);
        if (StringUtil.isNotNullOrEmpty(vo.getGoodsDesc())) {
            good.setGoodsDesc(vo.getGoodsDesc());
        }
        if (vo.getGoodsPriceOrigin() != null) {
            good.setGoodsPriceOrigin(vo.getGoodsPriceOrigin());
        }
        if (vo.getGoodsPriceVip() != null) {
            good.setGoodsPriceVip(vo.getGoodsPriceVip());
        }
        goodsRepository.save(good);
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        result.setMessage("创建成功");
        return result;
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