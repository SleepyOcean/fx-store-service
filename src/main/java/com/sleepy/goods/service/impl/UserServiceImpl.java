package com.sleepy.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sleepy.goods.dto.CartDTO;
import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.ExtraDTO;
import com.sleepy.goods.dto.UserDTO;
import com.sleepy.goods.entity.AddressEntity;
import com.sleepy.goods.entity.GoodsEntity;
import com.sleepy.goods.entity.UserEntity;
import com.sleepy.goods.repository.AddressRepository;
import com.sleepy.goods.repository.GoodsRepository;
import com.sleepy.goods.repository.OrderRepository;
import com.sleepy.goods.repository.UserRepository;
import com.sleepy.goods.service.UserService;
import com.sleepy.goods.util.HttpUtil;
import com.sleepy.goods.util.StringUtil;
import com.sleepy.goods.vo.user.AddressDelVO;
import com.sleepy.goods.vo.user.AddressNewVO;
import com.sleepy.goods.vo.user.AddressVO;
import com.sleepy.goods.vo.user.UserVO;
import com.sleepy.jpql.JpqlParser;
import com.sleepy.jpql.ParserParameter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.*;

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
    GoodsRepository goodsRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    JpqlParser jpqlParser;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public CommonDTO<UserDTO> getUserInfoById(String id) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("id", id);
        List<UserDTO> entities = findUser(parameters);
        return getUserDetailResult(entities);
    }

    private CommonDTO<UserDTO> getUserDetailResult(List<UserDTO> entities) {
        CommonDTO<UserDTO> result = new CommonDTO<>();
        UserDTO data = entities.get(0);
        String cartString = data.getCartInfo();
        if (StringUtil.isNotNullOrEmpty(cartString)) {
            JSONObject carts = JSON.parseObject(cartString);
            Map<String, CartDTO> cartsMap = StringUtil.jsonObjectToMap(carts);
            List<GoodsEntity> goods = goodsRepository.findAllByGoodsIdIn(new ArrayList<>(cartsMap.keySet()));
            result.setExtra(StringUtil.getNewExtraMap(new ExtraDTO("goods", goods),
                    new ExtraDTO("carts", new ArrayList<>(cartsMap.values())),
                    new ExtraDTO("orders", orderRepository.findByUserId(data.getUserId()))));
        } else {
            result.setExtra(StringUtil.getNewExtraMap(new ExtraDTO("orders", orderRepository.findByUserId(data.getUserId()))));
        }
        data.setCartInfo(null);
        result.setResult(data);
        return result;
    }

    @Override
    public CommonDTO<UserDTO> getUserInfoByCode(String code) throws Exception {
        Map<String, Object> parameters = new HashMap<>(4);
        String openId = getWeixinOpenId(code);
        parameters.put("openId", openId);
        List<UserDTO> entities = findUser(parameters);
        if (entities.size() > 0) {
            return getUserDetailResult(entities);
        } else {
            UserVO vo = new UserVO();
            vo.setWxOpenId(openId);
            return saveUser(vo);
        }
    }

    @Override
    public CommonDTO<UserDTO> saveUser(UserVO vo) throws Exception {
        CommonDTO<UserDTO> result = new CommonDTO<>();
        String openId = StringUtil.isNotNullOrEmpty(vo.getWxOpenId()) ? vo.getWxOpenId() : getWeixinOpenId(vo.getWxCode());
        UserEntity user;
        try {
            user = userRepository.findByOpenId(openId).get();
        } catch (NoSuchElementException e) {
            user = new UserEntity();
            user.setOpenId(openId);
        }
        if (!StringUtil.isNullOrEmpty(vo.getUserName())) {
            user.setUserName(vo.getUserName());
        } else {
            user.setUserName("微信用户" + openId.substring(3, 6));
        }
        UserEntity entity = userRepository.saveAndFlush(user);
        UserDTO data = new UserDTO();
        BeanUtils.copyProperties(entity, data);
        result.setResult(data);
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
            UserEntity entity = userRepository.saveAndFlush(user);
            UserDTO data = new UserDTO();
            BeanUtils.copyProperties(entity, data);
            result.setResult(data);
            result.setMessage("更新成功");
        } else {
            throw new Exception("userId 不能为空，请确认参数是否完备");
        }
        return result;
    }

    @Override
    public CommonDTO<AddressEntity> addAddress(AddressNewVO vo) {
        CommonDTO<AddressEntity> result = new CommonDTO<>();
        AddressEntity entity = new AddressEntity(vo);
        UserEntity user = userRepository.findByUserId(vo.getUserId()).get();
        entity = addressRepository.saveAndFlush(entity);
        if (StringUtil.isNullOrEmpty(user.getDefaultAddressId())) {
            user.setDefaultAddressId(entity.getAddressId());
            userRepository.saveAndFlush(user);
        }
        result.setResult(entity);
        return result;
    }

    @Override
    public CommonDTO<AddressEntity> getAddressInfo(String addressId, String userId) throws Exception {
        CommonDTO<AddressEntity> result = new CommonDTO<>();
        AddressEntity entity = addressRepository.findById(addressId).get();
        if (entity.getUserId().equals(userId)) {
            result.setResult(entity);
        } else {
            StringUtil.throwExceptionInfo("userId与地址信息不匹配");
        }
        return result;
    }

    @Override
    public CommonDTO<AddressEntity> getAddressInfoByUserId(String userId) {
        CommonDTO<AddressEntity> result = new CommonDTO<>();
        result.setResultList(addressRepository.findAllByUserId(userId));
        result.setExtra(StringUtil.getNewExtraMap(new ExtraDTO("defaultAddressId", userRepository.findByUserId(userId).get().getDefaultAddressId())));
        return result;
    }

    @Override
    public CommonDTO<AddressEntity> setDefaultAddress(String addressId, String userId) {
        CommonDTO<AddressEntity> result = new CommonDTO<>();
        UserEntity entity = userRepository.findByUserId(userId).get();
        entity.setDefaultAddressId(addressId);
        userRepository.saveAndFlush(entity);
        return result;
    }

    @Override
    public CommonDTO<AddressEntity> updateAddress(AddressVO vo) throws Exception {
        CommonDTO<AddressEntity> result = new CommonDTO<>();
        if (StringUtil.isNotNullOrEmpty(vo.getAddressId())) {
            AddressEntity entity = addressRepository.findById(vo.getAddressId()).get();
            if (StringUtil.isNotNullOrEmpty(vo.getContact())) {
                entity.setContact(vo.getContact());
            }
            if (StringUtil.isNotNullOrEmpty(vo.getContactName())) {
                entity.setContactName(vo.getContactName());
            }
            if (StringUtil.isNotNullOrEmpty(vo.getContactAddress())) {
                entity.setContactAddress(vo.getContactAddress());
            }
            result.setResult(addressRepository.saveAndFlush(entity));
        } else {
            StringUtil.throwExceptionInfo("地址信息ID不能为空");
        }
        return result;
    }

    @Override
    public CommonDTO<AddressEntity> deleteAddress(AddressDelVO vo) throws Exception {
        CommonDTO<AddressEntity> result = new CommonDTO<>();
        UserEntity userEntity = userRepository.findByUserId(vo.getUserId()).get();
        StringBuilder message = new StringBuilder();
        if (vo.getDeleteAddressIds() != null && vo.getDeleteAddressIds().size() > 0) {
            if (StringUtil.isNotNullOrEmpty(vo.getDefaultAddressId())) {
                userEntity.setDefaultAddressId(vo.getDefaultAddressId());
                userRepository.saveAndFlush(userEntity);
            }
            addressRepository.findAllByAddressIdIn(vo.getDeleteAddressIds()).forEach(address -> {
                if (vo.getUserId().equals(address.getUserId())) {
                    addressRepository.delete(address);
                } else {
                    if (message.length() > 0) {
                        message.append(address.getAddressId() + " ");
                    } else {
                        message.append("，警告：地址id集合中包含非此用户的地址id：");
                    }
                }
            });
            List<AddressEntity> addressEntities = addressRepository.findAllByUserId(vo.getUserId());
            if (null == addressEntities || addressEntities.size() < 1) {
                userEntity.setDefaultAddressId("");
                userRepository.saveAndFlush(userEntity);
            }
            result.setMessage("删除成功" + message.toString());
        } else {
            StringUtil.throwExceptionInfo("地址信息id集合deleteAddressIds不能为空");
        }
        return result;
    }

    private List<UserDTO> findUser(Map<String, Object> parameters) {
        if (StringUtil.isNullOrEmpty(parameters.get("userId")) && StringUtil.isNullOrEmpty(parameters.get("openId"))) {
            return new ArrayList<>();
        }
        String sql = jpqlParser.parse(new ParserParameter("userJpql.findUser", parameters, "mysql")).getExecutableSql();
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(UserDTO.class);
        List<UserDTO> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    private String getWeixinOpenId(String wxCode) throws Exception {
        if (!StringUtil.isNullOrEmpty(wxCode)) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + "wx8dfe7072afd33eaa" + "&secret=" + "f43ff7c674a0b751d5752c9f4f210bf9" + "&js_code=" + wxCode + "&grant_type=" + wxCode;
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