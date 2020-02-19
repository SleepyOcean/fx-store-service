package com.sleepy.goods.dto;

import lombok.Data;

/**
 * ExtraDTO
 *
 * @author gehoubao
 * @create 2020-02-17 12:37
 **/
@Data
public class ExtraDTO {
    private String key;
    private Object value;

    public ExtraDTO(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}