package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.UserDTO;
import com.sleepy.goods.vo.UserVO;

/**
 * 用户Service
 *
 * @author gehoubao
 * @create 2020-02-15 18:44
 **/

public interface UserService {
    CommonDTO<UserDTO> getUserInfoById(String id) throws Exception;

    CommonDTO<UserDTO> getUserInfoByCode(String code) throws Exception;

    CommonDTO<UserDTO> saveUser(UserVO vo) throws Exception;

    CommonDTO<UserDTO> updateUser(UserVO vo) throws Exception;

}
