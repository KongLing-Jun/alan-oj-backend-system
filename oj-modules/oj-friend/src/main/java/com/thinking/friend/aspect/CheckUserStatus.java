package com.thinking.friend.aspect;

import java.lang.annotation.*;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : CheckUserStatus
 * @description : [用户状态注解]
 * @createTime : [2025/2/2 21:54]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 21:54]
 * @updateRemark : [v1.0]
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckUserStatus {

}
