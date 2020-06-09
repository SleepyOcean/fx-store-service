package com.sleepy.goods.service.impl;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.MapDTO;
import com.sleepy.goods.entity.*;
import com.sleepy.goods.repository.CategoryRepository;
import com.sleepy.goods.repository.GoodsRepository;
import com.sleepy.goods.service.GoodsService;
import com.sleepy.goods.source.DataSourceGetter;
import com.sleepy.goods.source.DataSourceSetter;
import com.sleepy.goods.util.ClassUtil;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.goods.*;
import com.sleepy.jpql.JpqlExecutor;
import com.sleepy.jpql.JpqlResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    DataSourceSetter dataSourceSetter;
    @Autowired
    DataSourceGetter dataSourceGetter;

    @Override
    public CommonDTO<GoodsEntity> getGoodsList(GoodsVO vo) {
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        Map<String, Object> params = StringUtil.newParamsMap(new MapDTO("limit", vo.getPageSize()),
                new MapDTO("offset", (vo.getPage() - 1) * vo.getPageSize()));
        if (vo.getCategory() != null) {
            params.put("category", vo.getCategory());
        }
        if (vo.getSubCategory() != null) {
            params.put("subCategory", vo.getSubCategory());
        }
        if (StringUtil.isNotNullOrEmpty(vo.getGoodsName())) {
            params.put("goodsNameLike", "%" + vo.getGoodsName() + "%");
        }
        JpqlResultSet set = jpqlExecutor.exec("goods.findGoods", params, GoodsEntity.class);
        List<GoodsEntity> data = set.getResultList();
        List<String> goodsIds = data.stream().map(g -> g.getGoodsId()).collect(Collectors.toList());
        result.setResultList(data);
        result.setTotal(set.getTotal());
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
        good = dataSourceSetter.saveGood(good);
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        result.setResult(good);
        result.setMessage("创建成功");
        return result;
    }

    @Override
    public CommonDTO<GoodsEntity> updateGoods(GoodsVO vo) {
        GoodsEntity entity = dataSourceGetter.getGoods(vo.getGoodsId());
        ClassUtil.updateValue(vo, entity);
        dataSourceSetter.saveGood(entity);
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<GoodsSpecEntity> saveSpec(GoodsSpecNewVO vo) {
        GoodsSpecEntity entity = new GoodsSpecEntity(vo);
        entity = dataSourceSetter.saveGoodSpec(entity);
        CommonDTO<GoodsSpecEntity> result = new CommonDTO<>();
        result.setResult(entity);
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecEntity> updateSpec(GoodsSpecVO vo) {
        GoodsSpecEntity entity = dataSourceGetter.getGoodSpec(vo.getSpecId());
        ClassUtil.updateValue(vo, entity);
        entity = dataSourceSetter.saveGoodSpec(entity);
        CommonDTO<GoodsSpecEntity> result = new CommonDTO<>();
        result.setResult(entity);
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecEntity> deleteSpec(GoodsSpecVO vo) {
        CommonDTO<GoodsSpecEntity> result = new CommonDTO<>();
        dataSourceSetter.deleteGoodSpec(vo.getSpecId());
        result.setMessage("【商品规格】删除成功");
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecKeyEntity> saveSpecKey(GoodsSpecNewKeyVO vo) {
        GoodsSpecKeyEntity entity = new GoodsSpecKeyEntity(vo);
        entity = dataSourceSetter.saveGoodSpecKey(entity);
        CommonDTO<GoodsSpecKeyEntity> result = new CommonDTO<>();
        result.setResult(entity);
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecKeyEntity> updateSpecKey(GoodsSpecKeyVO vo) {
        GoodsSpecKeyEntity entity = dataSourceGetter.getGoodSpecKey(vo.getSpecKeyId());
        if (StringUtil.isNotNullOrEmpty(vo.getSpecName())) {
            entity.setSpecName(vo.getSpecName());
        }
        entity = dataSourceSetter.saveGoodSpecKey(entity);
        CommonDTO<GoodsSpecKeyEntity> result = new CommonDTO<>();
        result.setResult(entity);
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecKeyEntity> deleteSpecKey(GoodsSpecKeyVO vo) {
        CommonDTO<GoodsSpecKeyEntity> result = new CommonDTO<>();
        dataSourceSetter.deleteGoodSpecKey(vo.getSpecKeyId());
        result.setMessage("【商品规格Key】删除成功");
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecValueEntity> saveSpecValue(GoodsSpecNewValueVO vo) {
        GoodsSpecValueEntity entity = new GoodsSpecValueEntity(vo);
        entity = dataSourceSetter.saveGoodSpecValue(entity);
        CommonDTO<GoodsSpecValueEntity> result = new CommonDTO<>();
        result.setResult(entity);
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecValueEntity> updateSpecValue(GoodsSpecValueVO vo) {
        GoodsSpecValueEntity entity = dataSourceGetter.getGoodSpecValue(vo.getSpecValueId());
        if (StringUtil.isNotNullOrEmpty(vo.getSpecValue())) {
            entity.setSpecValue(vo.getSpecValue());
        }
        entity = dataSourceSetter.saveGoodSpecValue(entity);
        CommonDTO<GoodsSpecValueEntity> result = new CommonDTO<>();
        result.setResult(entity);
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecValueEntity> deleteSpecValue(GoodsSpecValueVO vo) {
        CommonDTO<GoodsSpecValueEntity> result = new CommonDTO<>();
        dataSourceSetter.deleteGoodSpecValue(vo.getSpecValueId());
        result.setMessage("【商品规格Value】删除成功");
        return result;
    }

    @Override
    public CommonDTO<GoodsSpecEntity> getSpecByGoodsId(String goodsId) {
        CommonDTO<GoodsSpecEntity> result = new CommonDTO<>();
        List<GoodsSpecEntity> data = dataSourceGetter.getGoodSpecList(goodsId);
        result.setResultList(data);
        return result;
    }

    private List<CategoryEntity> getGoodsCategory(int categoryCode) {
        return categoryRepository.findAllByCategoryType(categoryCode);
    }


}