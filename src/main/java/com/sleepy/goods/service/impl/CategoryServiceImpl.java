package com.sleepy.goods.service.impl;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.CategoryEntity;
import com.sleepy.goods.repository.CategoryRepository;
import com.sleepy.goods.service.CategoryService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.category.CategoryNewVO;
import com.sleepy.goods.vo.category.CategoryUpdateVO;
import com.sleepy.goods.vo.category.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SleepyOcean
 * @create 2020-03-08 15:06:01
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void save(CategoryNewVO category) {
        CategoryEntity entity = new CategoryEntity(category);
        if (StringUtil.isNotNullOrEmpty(category.getSubCategory())) {
            entity.setSubCategory(category.getSubCategory());
        }
        if (StringUtil.isNotNullOrEmpty(category.getCategoryImgUrl())) {
            entity.setCategoryImgUrl(category.getCategoryImgUrl());
        }
        categoryRepository.saveAndFlush(entity);
    }

    @Override
    public CommonDTO<CategoryEntity> getGoodsCategory(CategoryVO vo) {
        CommonDTO<CategoryEntity> result = new CommonDTO<>();
        List<CategoryEntity> data = categoryRepository.findAllByCategoryType(1);
        result.setResultList(data);
        return result;
    }

    @Override
    public CommonDTO<CategoryEntity> updateCategory(CategoryUpdateVO vo) {
        CategoryEntity entity = categoryRepository.getOne(vo.getCategoryId());
        if (StringUtil.isNotNullOrEmpty(vo.getCategoryName())) {
            entity.setCategoryName(vo.getCategoryName());
        }
        if (StringUtil.isNotNullOrEmpty(vo.getSubCategory())) {
            entity.setSubCategory(vo.getSubCategory());
        }
        if (StringUtil.isNotNullOrEmpty(vo.getCategoryImgUrl())) {
            entity.setCategoryImgUrl(vo.getCategoryImgUrl());
        }
        categoryRepository.saveAndFlush(entity);
        return new CommonDTO<>();
    }
}
