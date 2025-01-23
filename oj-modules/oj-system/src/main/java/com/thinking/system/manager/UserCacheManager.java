package com.thinking.system.manager;

import com.thinking.common.core.constants.CacheConstants;
import com.thinking.common.redis.service.RedisService;
import com.thinking.system.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserCacheManager
 * @description : [用户缓存管理器]
 * @createTime : [2025/1/23 23:35]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:35]
 * @updateRemark : [v1.0]
 */
@Component
public class UserCacheManager {

    private final RedisService redisService;

    public UserCacheManager(RedisService redisService) {
        this.redisService = redisService;
    }

    public void updateStatus(Long userId, Integer status) {
        //刷新用户缓存
        String userKey = CacheConstants.USER_DETAIL + userId;
        User user = redisService.getCacheObject(userKey, User.class);
        if (user != null) {
            user.setStatus(status);
            redisService.setCacheObject(userKey, user);
            //设置用户缓存有效期为10分钟
            redisService.expire(userKey, CacheConstants.USER_EXP, TimeUnit.MINUTES);
        }
    }
}
