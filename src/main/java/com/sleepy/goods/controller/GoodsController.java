package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.service.GoodsService;
import com.sleepy.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonDTO<GoodsEntity> get() {
        return goodsService.getGoodsList(new GoodsVO());
    }

    @GetMapping("/search")
    public CommonDTO<GoodsEntity> search(@RequestParam("goodsName") String goodsName) {
        return goodsService.searchGoodsList(goodsName);
    }

    @PostMapping("/save")
    public CommonDTO<GoodsEntity> save(@RequestBody GoodsVO vo) throws Exception {
        return goodsService.saveGoodsList(vo);
    }
}