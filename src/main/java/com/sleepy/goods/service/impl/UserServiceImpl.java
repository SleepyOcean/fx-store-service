package com.sleepy.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.UserDTO;
import com.sleepy.goods.entity.UserEntity;
import com.sleepy.goods.repository.UserRepository;
import com.sleepy.goods.service.UserService;
import com.sleepy.goods.util.HttpUtil;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.UserVO;
import com.sleepy.jpql.JpqlParser;
import com.sleepy.jpql.ParserParameter;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 用户ServiceImpl
 *
 * @author gehoubao
 * @create 2020-02-15 18:44
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JpqlParser jpqlParser;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public CommonDTO<UserDTO> getUserInfoById(String id) {
        CommonDTO<UserDTO> result = new CommonDTO<>();
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("id", id);
        List<UserDTO> entities = findUser(parameters);
        result.setResult(entities.get(0));
        return result;
    }

    @Override
    public CommonDTO<UserDTO> getUserInfoByCode(String code) throws Exception {
        CommonDTO<UserDTO> result = new CommonDTO<>();
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("openId", code);
        List<UserDTO> entities = findUser(parameters);
        if (entities.size() > 0) {
            result.setResult(entities.get(0));
            return result;
        } else {
            UserVO vo = new UserVO();
            vo.setWxCode(code);
            return saveUser(vo);
        }
    }

    @Override
    public CommonDTO<UserDTO> saveUser(UserVO vo) throws Exception {
        CommonDTO<UserDTO> result = new CommonDTO<>();
        String openId = getWeixinOpenId(vo);
        UserEntity user;
        try {
            user = userRepository.findByOpenId(openId).get();
        } catch (NoSuchElementException e) {
            user = new UserEntity();
            user.setOpenId(openId);
        }
        if (!StringUtil.isNullOrEmpty(vo.getUserName())) {
            user.setUserName(vo.getUserName());
        }
        if (!StringUtil.isNullOrEmpty(vo.getDeliveryInfo())) {
            user.setDeliveryInfo(vo.getDeliveryInfo());
        }
        UserEntity entity = userRepository.saveAndFlush(user);
        entity.setOpenId(null);
        Map<String, Object> extra = new HashMap<>(4);
        extra.put("data", entity);
        result.setExtra(extra);
        result.setMessage("保存成功");

        return result;
    }

    @Override
    public CommonDTO<UserDTO> updateUser(UserVO vo) throws Exception {
        CommonDTO<UserDTO> result = new CommonDTO<>();
        if (!StringUtil.isNullOrEmpty(vo.getUserId())) {
            UserEntity user = new UserEntity();
            user.setUserId(vo.getUserId());
            if (!StringUtil.isNullOrEmpty(vo.getUserName())) {
                user.setUserName(vo.getUserName());
            }
            if (!StringUtil.isNullOrEmpty(vo.getDeliveryInfo())) {
                user.setDeliveryInfo(vo.getDeliveryInfo());
            }
            UserEntity entity = userRepository.saveAndFlush(user);
            entity.setOpenId(null);
            Map<String, Object> extra = new HashMap<>(4);
            extra.put("data", entity);
            result.setExtra(extra);
            result.setMessage("更新成功");
        } else {
            throw new Exception("userId 不能为空，请确认参数是否完备");
        }
        return result;
    }

    private List<UserDTO> findUser(Map<String, Object> parameters) {
        String sql = jpqlParser.parse(new ParserParameter("userJpql.findUser", parameters, "mysql")).getExecutableSql();
        Query query = getSession().createNativeQuery(sql).addEntity(UserDTO.class);
        return query.getResultList();
    }

    private String getWeixinOpenId(UserVO vo) throws Exception {
        if (!StringUtil.isNullOrEmpty(vo.getWxCode())) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + "wx8dfe7072afd33eaa" + "&secret=" + "f43ff7c674a0b751d5752c9f4f210bf9" + "&js_code=" + vo.getWxCode() + "&grant_type=" + vo.getWxCode();
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

    @Data
    class WeixinAuthResult {
        private String session_key;
        private String openid;
    }
}