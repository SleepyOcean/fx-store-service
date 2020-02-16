package com.sleepy.goods.repository;

import com.sleepy.goods.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 用户表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByOpenId(String openId);
}
