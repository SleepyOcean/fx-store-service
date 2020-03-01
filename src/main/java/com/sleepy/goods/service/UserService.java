package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.UserDTO;
import com.sleepy.goods.entity.AddressEntity;
import com.sleepy.goods.vo.user.AddressDelVO;
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
    /**
     * 通过用户ID获取用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    CommonDTO<UserDTO> getUserInfoById(String id) throws Exception;

    /**
     * 通过微信Code获取用户信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    CommonDTO<UserDTO> getUserInfoByCode(String code) throws Exception;

    /**
     * 保存用户信息
     *
     * @param vo
     * @return
     * @throws Exception
     */
    CommonDTO<UserDTO> saveUser(UserVO vo) throws Exception;

    /**
     * 更新用户信息
     *
     * @param vo
     * @return
     * @throws Exception
     */
    CommonDTO<UserDTO> updateUser(UserVO vo) throws Exception;

    /**
     * 添加地址
     *
     * @param vo
     * @return
     */
    CommonDTO<AddressEntity> addAddress(AddressNewVO vo);

    /**
     * 获取地址信息
     *
     * @param addressId
     * @param userId
     * @return
     * @throws Exception
     */
    CommonDTO<AddressEntity> getAddressInfo(String addressId, String userId) throws Exception;

    /**
     * 通过userId获取地址信息
     *
     * @param userId
     * @return
     */
    CommonDTO<AddressEntity> getAddressInfoByUserId(String userId);

    /**
     * 设置默认地址
     *
     * @param addressId
     * @param userId
     * @return
     */
    CommonDTO<AddressEntity> setDefaultAddress(String addressId, String userId);

    /**
     * 更新地址信息
     *
     * @param vo
     * @return
     * @throws Exception
     */
    CommonDTO<AddressEntity> updateAddress(AddressVO vo) throws Exception;

    /**
     * 删除地址
     *
     * @param vo
     * @return
     * @throws Exception
     */
    CommonDTO<AddressEntity> deleteAddress(AddressDelVO vo) throws Exception;

}
