package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.UserDTO;
import com.sleepy.goods.service.UserService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器Controller
 *
 * @author gehoubao
 * @create 2020-02-15 18:39
 **/
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/get")
    public CommonDTO<UserDTO> get(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "id", required = false) String id) throws Exception {
        if (!StringUtil.isNullOrEmpty(id)) {
            return userService.getUserInfoById(id);
        } else if (!StringUtil.isNullOrEmpty(code)) {
            return userService.getUserInfoByCode(code);
        } else {
            CommonDTO<UserDTO> result = new CommonDTO<>();
            result.setMessage("参数缺失，请检查是否传递了code或id");
            return result;
        }
    }

    @PostMapping("/save")
    public CommonDTO<UserDTO> save(@RequestBody UserVO vo) throws Exception {
        return userService.saveUser(vo);
    }

    @PostMapping("/update")
    public CommonDTO<UserDTO> update(@RequestBody UserVO vo) throws Exception {
        return userService.updateUser(vo);
    }
}