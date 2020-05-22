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
    CommonDTO<UserDTO> getUserInfoById(long id) throws Exception;

    /**
     * 通过微信Code获取用户信息
     *
     * @param code
     * @param contact
     * @return
     * @throws Exception
     */
    CommonDTO<UserDTO> getUserInfoByCode(String code, String contact) throws Exception;

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
    CommonDTO<AddressEntity> getAddressInfo(long addressId, long userId) throws Exception;

    /**
     * 通过userId获取地址信息
     *
     * @param userId
     * @return
     */
    CommonDTO<AddressEntity> getAddressInfoByUserId(long userId);

    /**
     * 设置默认地址
     *
     * @param addressId
     * @param userId
     * @return
     */
    CommonDTO<AddressEntity> setDefaultAddress(long addressId, long userId);

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

    /**
     * 商家验证
     *
     * @param vo
     * @return
     */
    CommonDTO<UserDTO> merchantAuth(UserVO vo) throws Exception;
}
