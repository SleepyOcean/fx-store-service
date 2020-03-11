package com.sleepy.goods.controller;

import com.alibaba.fastjson.JSON;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.CategoryEntity;
import com.sleepy.goods.service.CategoryService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.category.CategoryNewVO;
import com.sleepy.goods.vo.category.CategoryUpdateVO;
import com.sleepy.goods.vo.category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author SleepyOcean
 * @create 2020-03-08 15:06:01
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/saveAll")
    public CommonDTO<String> saveAll(@RequestBody @Valid CategoryNewVO vo, BindingResult bindingResult) throws Exception {
        CommonDTO<String> result = new CommonDTO<>();
        if (vo.getCategories() != null && vo.getCategories().size() > 0) {
            for (CategoryNewVO category : vo.getCategories()) {
                category.setSubCategory(JSON.toJSONString(category.getSubCategoryObject()));
                categoryService.save(category);
            }
        } else {
            StringUtil.throwExceptionInfo("分类数组categories不能为空");
        }
        result.setMessage("创建成功");
        return result;
    }

    @PostMapping("/save")
    public CommonDTO<String> save(@RequestBody @Valid CategoryNewVO vo, BindingResult bindingResult) throws Exception {
        categoryService.save(vo);
        return new CommonDTO<>();
    }

    @PostMapping("/goods")
    public CommonDTO<CategoryEntity> getGoodsCategory(@RequestBody CategoryVO vo) throws Exception {
        return categoryService.getGoodsCategory(vo);
    }

    @PostMapping("/update")
    public CommonDTO<CategoryEntity> getGoodsCategory(@RequestBody @Valid CategoryUpdateVO vo, BindingResult bindingResult) throws Exception {
        return categoryService.updateCategory(vo);
    }
}
