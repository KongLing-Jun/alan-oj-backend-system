package com.thinking.system.domain.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : SysUserVO
 * @description : [系统用户VO]
 * @createTime : [2025/1/22 23:49]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:49]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class SysUserVO {

    @Schema(description = "用户账号")
    private String userAccount;

    @Schema(description = "用户昵称")
    private String nickName;
}
