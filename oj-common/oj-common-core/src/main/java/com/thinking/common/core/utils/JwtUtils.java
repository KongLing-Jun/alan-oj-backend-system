package com.thinking.common.core.utils;

import com.thinking.common.core.constants.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : JwtUtils
 * @description : [JWT工具类]
 * @createTime : [2025/1/19 23:49]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/19 23:49]
 * @updateRemark : [v1.0]
 */
public class JwtUtils {

    /**
     * 生成令牌
     *
     * @param claims 数据
     * @param secret 密钥
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims, String secret) {
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 从令牌中获取数据
     *
     * @param token  令牌
     * @param secret 密钥
     * @return 数据
     */
    public static Claims parseToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public static String getUserKey(Claims claims) {
        return toStr(claims.get(JwtConstants.LOGIN_USER_KEY));
    }

    public static String getUserId(Claims claims) {
        return toStr(claims.get(JwtConstants.LOGIN_USER_ID));
    }

    private static String toStr(Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
