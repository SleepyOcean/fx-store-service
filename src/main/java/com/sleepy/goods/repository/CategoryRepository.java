package com.sleepy.goods.repository;

import com.sleepy.goods.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 分类表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    List<CategoryEntity> findAllByCategoryCode(int categoryCode);
}
