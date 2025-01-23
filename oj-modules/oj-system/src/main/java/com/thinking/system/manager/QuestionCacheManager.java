package com.thinking.system.manager;

import com.thinking.common.core.constants.CacheConstants;
import com.thinking.common.redis.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionCacheManager
 * @description : [题目缓存管理器]]
 * @createTime : [2025/1/23 23:34]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:34]
 * @updateRemark : [v1.0]
 */
@Component
public class QuestionCacheManager {

    private final RedisService redisService;

    public QuestionCacheManager(RedisService redisService) {
        this.redisService = redisService;
    }

    public void addCache(Long questionId) {
        redisService.leftPushForList(CacheConstants.QUESTION_LIST, questionId);
    }

    public void removeCache(Long questionId) {
        redisService.removeForList(CacheConstants.QUESTION_LIST, questionId);
    }
}
