package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.UserDTO;
import com.sleepy.goods.entity.AddressEntity;
import com.sleepy.goods.service.UserService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.user.AddressDelVO;
import com.sleepy.goods.vo.user.AddressNewVO;
import com.sleepy.goods.vo.user.AddressVO;
import com.sleepy.goods.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/getById")
    public CommonDTO<UserDTO> getById(@RequestParam("userId") String id) throws Exception {
        if (!StringUtil.isNullOrEmpty(id)) {
            return userService.getUserInfoById(id);
        } else {
            CommonDTO<UserDTO> result = new CommonDTO<>();
            result.setMessage("参数缺失，请检查是否传递了id");
            return result;
        }
    }

    @GetMapping("/getByCode")
    public CommonDTO<UserDTO> getByCode(@RequestParam("code") String code) throws Exception {
        if (!StringUtil.isNullOrEmpty(code)) {
            return userService.getUserInfoByCode(code);
        } else {
            CommonDTO<UserDTO> result = new CommonDTO<>();
            result.setMessage("参数缺失，请检查是否传递了code");
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

    @PostMapping("/address/add")
    public CommonDTO<AddressEntity> addAddress(@RequestBody @Valid AddressNewVO vo, BindingResult bindingResult) throws Exception {
        return userService.addAddress(vo);
    }

    @PostMapping("/address/update")
    public CommonDTO<AddressEntity> updateAddress(@RequestBody AddressVO vo) throws Exception {
        return userService.updateAddress(vo);
    }

    @PostMapping("/address/delete")
    public CommonDTO<AddressEntity> deleteAddress(@RequestBody @Valid AddressDelVO vo, BindingResult bindingResult) throws Exception {
        return userService.deleteAddress(vo);
    }

    @PostMapping("/address/setDefault")
    public CommonDTO<AddressEntity> setDefaultAddress(@RequestBody @Valid AddressVO vo, BindingResult bindingResult) throws Exception {
        return userService.setDefaultAddress(vo.getAddressId(), vo.getUserId());
    }

    @GetMapping("/address/get")
    public CommonDTO<AddressEntity> getAddressInfo(@RequestParam(value = "userId") String userId, @RequestParam(value = "addressId", required = false) String addressId) throws Exception {
        if (StringUtil.isNotNullOrEmpty(addressId)) {
            return userService.getAddressInfo(addressId, userId);
        } else {
            return userService.getAddressInfoByUserId(userId);
        }
    }
}