package com.sleepy.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.admin.AdminDTO;
import com.sleepy.goods.entity.AdminEntity;
import com.sleepy.goods.repository.AdminRepository;
import com.sleepy.goods.service.AdminService;
import com.sleepy.goods.util.HttpUtil;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.admin.AdminNewVO;
import com.sleepy.goods.vo.admin.AdminVO;
import com.sleepy.jpql.JpqlExecutor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * 管理员用户ServiceImpl
 *
 * @author gehoubao
 * @create 2020-02-15 18:44
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    JpqlExecutor jpqlExecutor;

    @Override
    public CommonDTO<AdminDTO> getAdminInfoById(String id) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("adminId", id);
        List<AdminDTO> entities = findAdmin(parameters);
        return null;
    }

    @Override
    public CommonDTO<AdminDTO> getAdminInfoByCode(String code) throws Exception {
        Map<String, Object> parameters = new HashMap<>(4);
        String openId = getWeixinOpenId(code);
        parameters.put("openId", openId);
        List<AdminDTO> entities = findAdmin(parameters);
        if (entities.size() > 0) {
            CommonDTO<AdminDTO> result = new CommonDTO<>();
            result.setResult(entities.get(0));
            return result;
        } else {
            AdminNewVO vo = new AdminNewVO();
            vo.setWxOpenId(openId);
            return saveAdmin(vo);
        }
    }

    @Override
    public CommonDTO<AdminDTO> saveAdmin(AdminNewVO vo) throws Exception {
        CommonDTO<AdminDTO> result = new CommonDTO<>();
        String openId = StringUtil.isNotNullOrEmpty(vo.getWxOpenId()) ? vo.getWxOpenId() : getWeixinOpenId(vo.getWxCode());
        AdminEntity user;
        try {
            user = adminRepository.findByOpenId(openId).get();
        } catch (NoSuchElementException e) {
            user = new AdminEntity();
            user.setOpenId(openId);
        }
        if (!StringUtil.isNullOrEmpty(vo.getAdminName())) {
            user.setAdminName(vo.getAdminName());
        } else {
            user.setAdminName("微信用户" + openId.substring(3, 6));
        }
        AdminEntity entity = adminRepository.saveAndFlush(user);
        AdminDTO data = new AdminDTO();
        BeanUtils.copyProperties(entity, data);
        result.setResult(data);
        result.setMessage("保存成功");

        return result;
    }

    @Override
    public CommonDTO<AdminDTO> updateAdmin(AdminVO vo) throws Exception {
        CommonDTO<AdminDTO> result = new CommonDTO<>();
        if (!StringUtil.isNullOrEmpty(vo.getAdminId())) {
            AdminEntity user = new AdminEntity();
            user.setAdminId(vo.getAdminId());
            if (!StringUtil.isNullOrEmpty(vo.getAdminName())) {
                user.setAdminName(vo.getAdminName());
            }
            AdminEntity entity = adminRepository.saveAndFlush(user);
            AdminDTO data = new AdminDTO();
            BeanUtils.copyProperties(entity, data);
            result.setResult(data);
            result.setMessage("更新成功");
        } else {
            throw new Exception("userId 不能为空，请确认参数是否完备");
        }
        return result;
    }

    private List<AdminDTO> findAdmin(Map<String, Object> parameters) {
        if (StringUtil.isNullOrEmpty(parameters.get("adminId")) && StringUtil.isNullOrEmpty(parameters.get("openId"))) {
            return new ArrayList<>();
        }
        List<AdminDTO> resultList = jpqlExecutor.exec("admin.findAdmin", parameters, AdminDTO.class).getResultList();
        return resultList;
    }

    private String getWeixinOpenId(String wxCode) throws Exception {
        if (!StringUtil.isNullOrEmpty(wxCode)) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + "wx07c1e4a472c50936" + "&secret=" + "e17ab601f0b47328da7d356be21c84da" + "&js_code=" + wxCode + "&grant_type=" + wxCode;
            String respond = HttpUtil.doGet(url);
            String openId = JSON.parseObject(respond).getString("openid");
            if (!StringUtil.isNullOrEmpty(openId)) {
                return openId;
            } else {
                throw new Exception("未获取到微信openId： " + respond);
            }
        } else {
            throw new Exception("wxCode 不能为空， 请确认参数是否完备");
        }
    }
}