package com.thinking.common.security.service;

import cn.hutool.core.lang.UUID;
import com.thinking.common.core.constants.CacheConstants;
import com.thinking.common.core.constants.JwtConstants;
import com.thinking.common.core.domain.LoginUser;
import com.thinking.common.core.utils.JwtUtils;
import com.thinking.common.redis.service.RedisService;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : TokenService
 * @description : [操作用户登录token的方法]
 * @createTime : [2025/1/20 19:32]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 19:32]
 * @updateRemark : [v1.0]
 */
//操作用户登录token的方法
@Service
public class TokenService {

    @Autowired
    private RedisService redisService;

    //注意日志框架导入时，使用org.slf4j.Logger,org.slf4j.LoggerFactory.
    private final static Logger logger = LoggerFactory.getLogger(TokenService.class);

    /**
     * 生成token
     * @param userId
     * @param secret
     * @param identity
     * @param nickName
     * @param avatar
     * @return
     */
    public String createToken(Long userId, String secret, Integer identity, String nickName, String avatar) {
        Map<String, Object> claims = new HashMap<>();
        String userKey = UUID.fastUUID().toString();
        claims.put(JwtConstants.LOGIN_USER_ID, userId);
        claims.put(JwtConstants.LOGIN_USER_KEY, userKey);
        String token = JwtUtils.createToken(claims, secret);
        //第三方机制中存储敏感的信息

        //身份认证具体还要存储那些信息   redis 表明用户身份字段  identity  1  表示普通用户  2 ： 表示管理员用户  对象
        //使用什么样的数据结构  String  key value    String   hash  list  zset  set
        //key 必须保证唯一     便于维护  统一前缀：logintoken:userId   userId是通过雪花算法生成的
        //自增  管理员  C端用户   1
        //过期时间我们怎么记录  过期时间应该定多长。     720分钟   2~3小时
        //String tokenKey = "logintoken:" + sysUser.getUserId();
        String tokenKey = getTokenKey(userKey);
        LoginUser loginUser = new LoginUser();
        loginUser.setIdentity(identity);
        loginUser.setNickName(nickName);
        loginUser.setAvatar(avatar);
        redisService.setCacheObject(tokenKey, loginUser, CacheConstants.EXP, TimeUnit.MINUTES);

        return token;
    }

    //延长token的有效时间，就是延长redis当中从存储的用于用户身份认证的敏感信息的有效时间    操作redis  token  --> 唯一标识
    //在身份认证通过之后才会调用的，并且在请求到达Controller层之前  在拦截器中调用
    public void extendTokenTime(Claims claims) {
        String userKey = getUserKey(claims);
        if (userKey == null) {
            return;
        }
        String tokenKey = getTokenKey(userKey);
        //720min  12个小时      剩余  180min 时候对它进行延长
        Long expire = redisService.getExpire(tokenKey, TimeUnit.MINUTES);
        if(null != expire && expire < CacheConstants.REFRESH_TIME){
            redisService.expire(tokenKey, CacheConstants.EXP, TimeUnit.MINUTES);
        }
    }

    //根据token获取用户信息
    public LoginUser getLoginUser(String token, String secret) {
        String userKey = getUserKey(token,secret);
        if (userKey == null) {
            return null;
        }
        return redisService.getCacheObject(getTokenKey(userKey), LoginUser.class);
    }

    //根据用户唯一标识删除用户信息
    public boolean deleteLoginUser(String token, String secret) {
        String userKey = getUserKey(token,secret);
        if (userKey == null) {
            return false;
        }
        return redisService.deleteObject(getTokenKey(userKey));
    }

    //根据用户唯一标识获取用户id
    public Long getUserId(@NonNull Claims claims) {
        return Long.valueOf(JwtUtils.getUserId(claims));  //获取jwt中的key
    }

    /**
     * 拼接token
     * @param userKey
     * @return
     */
    private String getTokenKey(String userKey) {
        return JwtConstants.LOGIN_USER_KEY + userKey;
    }

    /**
     * 获取用户key
     * @param claims
     * @return
     */
    public String getUserKey(@NonNull Claims claims) {
        return JwtUtils.getUserKey(claims); //获取jwt中的key
    }

    private String getUserKey(String token, String secret) {
        Claims claims = getClaims(token,secret);
        if (null == claims) {
            return null;
        }
        return JwtUtils.getUserKey(claims);
    }

    /**
     * 获取token中的信息
     * @param token
     * @param secret
     * @return
     */
    public Claims getClaims(String token, String secret) {
        Claims claims;
        try {
            claims = JwtUtils.parseToken(token, secret); //获取令牌中信息  解析payload中信息  存储着用户唯一标识信息
            if (null ==  claims) {
                logger.error("解析token：{}, 出现异常", token);
                return null;
            }
        } catch (Exception e) {
            logger.error("解析token：{}, 出现异常", token, e);
            return null;
        }
        return claims;
    }

    public void refreshLoginUser(String nickName, String avatar, String userKey) {
        String tokenKey = getTokenKey(userKey);
        LoginUser loginUser = redisService.getCacheObject(tokenKey, LoginUser.class);
        if (loginUser != null) {
            loginUser.setNickName(nickName);
            loginUser.setAvatar(avatar);
            redisService.setCacheObject(tokenKey, loginUser);
        }
    }
}
