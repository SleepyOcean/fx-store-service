package com.sleepy.goods.repository;

import com.sleepy.goods.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品表操作repository
 *
 * @author gehoubao
 * @create 2020-02-14 21:23
 **/

public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    List<AddressEntity> findAllByAddressIdIn(List<String> ids);
}
