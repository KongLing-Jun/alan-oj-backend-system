package com.thinking.system.domain.dto.sysUser;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserSaveDTO
 * @description : [用户保存DTO]
 * @createTime : [2025/1/22 23:39]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:39]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter

//参数合法性判断
public class UserSaveDTO {

    @Schema(description = "用户账号")
    private String userAccount;

    @Schema(description = "用户密码")
    private String password;
}