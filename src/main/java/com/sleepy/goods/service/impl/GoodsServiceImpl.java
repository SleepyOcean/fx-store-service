package com.sleepy.goods.service.impl;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.MapDTO;
import com.sleepy.goods.entity.CategoryEntity;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.repository.CategoryRepository;
import com.sleepy.goods.repository.GoodsRepository;
import com.sleepy.goods.service.GoodsService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.GoodsVO;
import com.sleepy.goods.vo.goods.GoodsNewVO;
import com.sleepy.goods.vo.goods.GoodsUpdateVO;
import com.sleepy.jpql.JpqlExecutor;
import com.sleepy.jpql.JpqlResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    JpqlExecutor<GoodsEntity> jpqlExecutor;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CommonDTO<GoodsEntity> getGoodsList(GoodsVO vo) {
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        JpqlResultSet resultSet = jpqlExecutor.exec("goods.findGoods",
                StringUtil.newParamsMap(new MapDTO("limit", vo.getPageSize()),
                        new MapDTO("offset", (vo.getPage() - 1) * vo.getPageSize())),
                GoodsEntity.class);
        result.setResultList(resultSet.getResultList());
        result.setTotal(resultSet.getTotal());
        List<CategoryEntity> goodsCategory = getGoodsCategory(1);
        result.setExtra(StringUtil.getNewExtraMap(new MapDTO("category", goodsCategory)));
        return result;
    }

    @Override
    public CommonDTO<GoodsEntity> saveGoods(GoodsNewVO vo) throws Exception {
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
        good.setUpdateTime(StringUtil.getDateString(new Date()));
        goodsRepository.save(good);
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        result.setMessage("创建成功");
        return result;
    }

    @Override
    public CommonDTO<GoodsEntity> searchGoodsList(GoodsVO vo) {
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        JpqlResultSet set = jpqlExecutor.exec("goods.findGoods",
                StringUtil.newParamsMap(new MapDTO("goodsNameLike", "%" + vo.getGoodsName() + "%"), new MapDTO("limit", vo.getPageSize()),
                        new MapDTO("offset", (vo.getPage() - 1) * vo.getPageSize())), GoodsEntity.class);
        List<GoodsEntity> data = set.getResultList();
        result.setResultList(data);
        result.setTotal(set.getTotal());
        return result;
    }

    @Override
    public CommonDTO<GoodsEntity> getByCategory(GoodsVO vo) {
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        JpqlResultSet set = jpqlExecutor.exec("goods.findGoods",
                StringUtil.newParamsMap(new MapDTO("category", vo.getCategory()), new MapDTO("limit", vo.getPageSize()),
                        new MapDTO("offset", (vo.getPage() - 1) * vo.getPageSize())), GoodsEntity.class);
        List<GoodsEntity> data = set.getResultList();
        result.setResultList(data);
        result.setTotal(set.getTotal());
        List<CategoryEntity> goodsCategory = getGoodsCategory(1);
        result.setExtra(StringUtil.getNewExtraMap(new MapDTO("category", goodsCategory)));
        return result;
    }

    @Override
    public CommonDTO<GoodsEntity> updateGoods(GoodsUpdateVO vo) {
        GoodsEntity entity = goodsRepository.findById(vo.getGoodsId()).get();
        entity.update(vo);
        entity.setUpdateTime(StringUtil.getDateString(new Date()));
        goodsRepository.saveAndFlush(entity);
        return new CommonDTO<>();
    }

    private List<CategoryEntity> getGoodsCategory(int categoryCode) {
        return categoryRepository.findAllByCategoryType(categoryCode);
    }


}