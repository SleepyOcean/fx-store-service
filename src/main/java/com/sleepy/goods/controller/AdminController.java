package com.sleepy.goods.controller;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.admin.AdminDTO;
import com.sleepy.goods.service.AdminService;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.admin.AdminNewVO;
import com.sleepy.goods.vo.admin.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员用户控制器Controller
 *
 * @author gehoubao
 * @create 2020-02-15 18:39
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/getById")
    public CommonDTO<AdminDTO> getById(@RequestParam("adminId") String id) throws Exception {
        if (!StringUtil.isNullOrEmpty(id)) {
            return adminService.getAdminInfoById(id);
        } else {
            CommonDTO<AdminDTO> result = new CommonDTO<>();
            result.setMessage("参数缺失，请检查是否传递了id");
            return result;
        }
    }

    @GetMapping("/getByCode")
    public CommonDTO<AdminDTO> getByCode(@RequestParam("code") String code, @RequestParam(value = "name", required = false) String name) throws Exception {
        if (!StringUtil.isNullOrEmpty(code)) {
            return adminService.getAdminInfoByCode(code);
        } else {
            CommonDTO<AdminDTO> result = new CommonDTO<>();
            result.setMessage("参数缺失，请检查是否传递了code");
            return result;
        }
    }

    @PostMapping("/save")
    public CommonDTO<AdminDTO> save(@RequestBody AdminNewVO vo) throws Exception {
        return adminService.saveAdmin(vo);
    }

    @PostMapping("/update")
    public CommonDTO<AdminDTO> update(@RequestBody AdminVO vo) throws Exception {
        return adminService.updateAdmin(vo);
    }

}