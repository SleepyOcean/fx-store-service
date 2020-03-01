package com.sleepy.goods.repository;

import com.sleepy.goods.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 管理员用户表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
    Optional<AdminEntity> findByOpenId(String openId);

    Optional<AdminEntity> findByAdminId(String adminId);
}
