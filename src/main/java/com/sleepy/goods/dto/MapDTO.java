package com.sleepy.goods.dto;

import lombok.Data;

/**
 * MapDTO
 *
 * @author gehoubao
 * @create 2020-02-17 12:37
 **/
@Data
public class MapDTO {
    private String key;
    private Object value;

    public MapDTO(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}