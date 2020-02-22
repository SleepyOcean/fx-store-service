package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.UserDTO;
import com.sleepy.goods.entity.AddressEntity;
import com.sleepy.goods.vo.user.AddressNewVO;
import com.sleepy.goods.vo.user.AddressVO;
import com.sleepy.goods.vo.user.UserVO;

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

    CommonDTO<AddressEntity> addAddress(AddressNewVO vo);

    CommonDTO<AddressEntity> getAddressInfo(String addressId);

    CommonDTO<AddressEntity> updateAddress(AddressVO vo) throws Exception;

    CommonDTO<AddressEntity> deleteAddress(AddressVO vo) throws Exception;
}
