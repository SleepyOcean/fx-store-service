package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.CategoryEntity;
import com.sleepy.goods.vo.category.CategoryNewVO;
import com.sleepy.goods.vo.category.CategoryUpdateVO;
import com.sleepy.goods.vo.category.CategoryVO;

/**
 * @author SleepyOcean
 * @create 2020-03-08 15:06:01
 */
public interface CategoryService {
    void save(CategoryNewVO category);

    CommonDTO<CategoryEntity> getGoodsCategory(CategoryVO vo);

    CommonDTO<CategoryEntity> updateCategory(CategoryUpdateVO vo);
}
