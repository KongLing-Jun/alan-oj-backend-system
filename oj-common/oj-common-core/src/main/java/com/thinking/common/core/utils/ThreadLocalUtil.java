package com.thinking.common.core.utils;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ThreadLocalUtil
 * @description : [用于在多线程环境下存储和获取线程局部变量]
 * @createTime : [2025/1/19 14:03]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/19 14:03]
 * @updateRemark : [v1.0]
 */
public class ThreadLocalUtil {

    //TransmittableThreadLocal是阿里巴巴开源的一个工具类，用于解决ThreadLocal在父子线程间传递的问题。
    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        return (T) map.getOrDefault(key, null);
    }

    private static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
