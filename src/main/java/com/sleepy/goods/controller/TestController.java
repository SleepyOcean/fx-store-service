package com.sleepy.goods.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Captain1920
 * @create 2020/2/14 15:19
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "【沉睡的海洋】：商品管理系统连接成功！";
    }
}
