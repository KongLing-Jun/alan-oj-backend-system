package com.thinking.system.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : BCryptUtils
 * @description : [加密算法工具类]
 * @createTime : [2025/1/22 23:07]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:07]
 * @updateRemark : [v1.0]
 */
public class BCryptUtils {

    /**
     * 生成加密后密文
     * @param password 密码
     * @return 加密后字符串
     */
    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 判断密码是否相同
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后密文
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

}
