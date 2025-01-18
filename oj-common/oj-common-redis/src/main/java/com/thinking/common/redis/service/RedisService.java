package com.thinking.common.redis.service;

import com.alibaba.fastjson2.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : RedisService
 * @description : [操作redis的工具类]
 * @createTime : [2025/1/18 23:34]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/18 23:34]
 * @updateRemark : [描述说明本次修改内容]
 */
@Component
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //************************ 操作key ***************************

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hashKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
//        return expire(key, timeout, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, TimeUnit.SECONDS));
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
//        return redisTemplate.expire(key, timeout, unit);
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获取剩余有效时间
     *
     * @param key  Redis键
     * @param unit 时间单位
     * @return 剩余有效时间
     */
    public Long getExpire(final String key, final TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key) {
//        return redisTemplate.delete(key);
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    //************************ 操作String类型 ***************************

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key, Class<T> clazz) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        ValueOperations<String, T> operationsT = (ValueOperations<String, T>) operations;
        T t = (T) operations.get(key);
        if (t instanceof String) {
            return t;
        }
        return JSON.parseObject(String.valueOf(t),clazz);
    }

    /**
     * 批量获取缓存对象
     * @param keyList
     * @param clazz
     * @return
     * @param <T>
     */
    public <T> List<T> multiGetCacheObject(final List<String> keyList, Class<T> clazz) {
        List<T> list = (List<T>) redisTemplate.opsForValue().multiGet(keyList);
        if (null == list || list.size() <= 0) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (Object t : list) {
            result.add(JSON.parseObject(String.valueOf(t), clazz));
        }
        return result;
    }

    /**
     * 批量设置缓存对象
     * @param map
     * @param <K>
     * @param <V>
     */
    public <K,V> void multiSetCacheObject(Map<? extends K, ? extends V> map) {
        redisTemplate.opsForValue().multiSet((Map<? extends String, ?>) map);
    }

    /**
     * 计数加一
     *
     * @param key
     * @return
     */
    public Long increment(final String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    //*************** 操作list结构 ****************

}
