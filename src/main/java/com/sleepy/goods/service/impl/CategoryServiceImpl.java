package com.sleepy.goods.service.impl;

import com.sleepy.goods.entity.CategoryEntity;
import com.sleepy.goods.repository.CategoryRepository;
import com.sleepy.goods.service.CategoryService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.category.CategoryNewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
