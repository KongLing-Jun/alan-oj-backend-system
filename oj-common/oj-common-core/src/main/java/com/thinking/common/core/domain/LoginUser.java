package com.thinking.common.core.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : LoginUser
 * @description : [登录用户信息类]
 * @createTime : [2025/1/20 17:37]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 17:37]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class LoginUser {

    private String nickName;    //用户昵称

    private String avatar;      //用户头像

    private Integer identity;   //用户身份 1 :表示普通用户  2 ：表示管理员用户
}
