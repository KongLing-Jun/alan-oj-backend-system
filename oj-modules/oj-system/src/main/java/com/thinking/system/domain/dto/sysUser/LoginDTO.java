package com.thinking.system.domain.dto.sysUser;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : LoginDTO
 * @description : [登录DTO]
 * @createTime : [2025/1/22 23:38]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:38]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class LoginDTO {

    private String userAccount;

    private String password;
}