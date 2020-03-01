package com.sleepy.goods.service;

import com.sleepy.goods.dto.CommonDTO;
import com.sleepy.goods.dto.admin.AdminDTO;
import com.sleepy.goods.vo.admin.AdminNewVO;
import com.sleepy.goods.vo.admin.AdminVO;

/**
 * 管理员用户Service
 *
 * @author gehoubao
 * @create 2020-02-15 18:44
 **/
public interface AdminService {
    /**
     * 通过用户ID获取用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    CommonDTO<AdminDTO> getAdminInfoById(String id) throws Exception;

    /**
     * 通过微信Code获取用户信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    CommonDTO<AdminDTO> getAdminInfoByCode(String code) throws Exception;

    /**
     * 保存用户信息
     *
     * @param vo
     * @return
     * @throws Exception
     */
    CommonDTO<AdminDTO> saveAdmin(AdminNewVO vo) throws Exception;

    /**
     * 更新用户信息
     *
     * @param vo
     * @return
     * @throws Exception
     */
    CommonDTO<AdminDTO> updateAdmin(AdminVO vo) throws Exception;
}
