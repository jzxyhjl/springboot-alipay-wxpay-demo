package com.example.utils;

import com.google.common.collect.Maps;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * Author:ggsk
 * CreateDate:2020/3/4
 **/
public class BeanMapUtil {

    public static <T> Map<String, String> beanToMap(T t) {
        Map<String, String> map = Maps.newHashMap();
        if (t != null) {
            BeanMap beanMap = BeanMap.create(t);
            beanMap.keySet().forEach(key -> {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key) + "");
                }
            });
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, String> map, Class<T> beanClass) throws IllegalAccessException, InstantiationException {
        T t = beanClass.newInstance();
        if (map == null) {
            return t;
        }
        BeanWrapper beanWrapper = new BeanWrapperImpl(t);
        beanWrapper.setPropertyValues(map);
        return t;
    }
}
