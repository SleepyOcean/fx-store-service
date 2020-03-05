package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.service.GoodsService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.GoodsVO;
import com.sleepy.goods.vo.goods.GoodsNewVO;
import com.sleepy.goods.vo.goods.GoodsUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 商品 Controller
 *
 * @author gehoubao
 * @create 2020-02-14 20:33
 **/
@RestController
@CrossOrigin
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/get")
    public CommonDTO<GoodsEntity> get(@RequestParam(value = "category", required = false) Integer category, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        GoodsVO vo = new GoodsVO();
        vo.setPage(page);
        vo.setPageSize(pageSize);
        if (category != null) {
            vo.setCategory(category);
            return goodsService.getByCategory(vo);
        } else {
            return goodsService.getGoodsList(vo);
        }
    }

    @GetMapping("/getByCategory")
    public CommonDTO<GoodsEntity> getByCategory(@RequestParam("category") Integer category, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) throws Exception {
        GoodsVO vo = new GoodsVO();
        vo.setCategory(category);
        vo.setPage(page);
        vo.setPageSize(pageSize);
        return goodsService.getByCategory(vo);
    }

    @GetMapping("/search")
    public CommonDTO<GoodsEntity> search(@RequestParam("goodsName") String goodsName, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        GoodsVO vo = new GoodsVO();
        vo.setGoodsName(goodsName);
        vo.setPage(page);
        vo.setPageSize(pageSize);
        return goodsService.searchGoodsList(vo);
    }

    @PostMapping("/update")
    public CommonDTO<GoodsEntity> update(@RequestBody @Valid GoodsUpdateVO vo, BindingResult bindingResult) throws Exception {
        return goodsService.updateGoods(vo);
    }

    @PostMapping("/save")
    public CommonDTO<GoodsEntity> save(@RequestBody @Valid GoodsNewVO vo, BindingResult bindingResult) throws Exception {
        return goodsService.saveGoodsList(vo);
    }

    @PostMapping("/saveAll")
    public CommonDTO<GoodsEntity> saveAll(@RequestBody GoodsVO vo, BindingResult bindingResult) throws Exception {
        CommonDTO<GoodsEntity> result = new CommonDTO<>();
        if (vo.getGoods() != null && vo.getGoods().size() > 0) {
            for (GoodsNewVO good : vo.getGoods()) {
                goodsService.saveGoodsList(good);
            }
        } else {
            StringUtil.throwExceptionInfo("商品数组goods不能为空");
        }
        result.setMessage("创建成功");
        return result;
    }
}