package com.sleepy.goods.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射操作工具类
 *
 * @author gehoubao
 * @create 2020-05-21 17:22
 **/
@Slf4j
public class ClassUtil {
    public static void updateValue(Object origin, Object dest) {
        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodGetter = getMethodString(fieldName, "get");
            String methodSetter = getMethodString(fieldName, "set");
            try {
                Object value = origin.getClass().getMethod(methodGetter).invoke(origin);
                if (value != null) {
                    dest.getClass().getMethod(methodSetter, value.getClass()).invoke(dest, value);
                }
            } catch (IllegalAccessException e) {
                log.error("[convert object error] IllegalAccessException {}", e.getMessage());
            } catch (InvocationTargetException e) {
                log.error("[convert object error] InvocationTargetException {}", e.getMessage());
            } catch (NoSuchMethodException e) {
                log.error("[convert object error] NoSuchMethodException {}", e.getMessage());
            }
        }
    }

    private static String getMethodString(String fieldName, String prefix) {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}