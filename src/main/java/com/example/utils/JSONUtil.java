package com.example.utils;

import com.google.gson.Gson;

import java.util.Map;

public class JSONUtil {
    private static Gson gson = new Gson();

    public static Map<String, String> jsonToMap(String json) {
        Map<String, String> resultMap = gson.fromJson(json, Map.class);
        return resultMap;
    }

    public static String mapToJson(Map<String, Object> map) {
        String json = gson.toJson(map);
        return json;
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        T result = gson.fromJson(json, clazz);
        return result;
    }

    public static <T> String beanToJson(T t, Class<T> clazz) {
        String json = gson.toJson(t, clazz);
        return json;
    }
}
