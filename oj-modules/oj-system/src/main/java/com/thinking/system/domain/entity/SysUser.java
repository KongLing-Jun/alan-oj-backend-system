package com.thinking.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thinking.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : SysUser
 * @description : [系统用户实体类]
 * @createTime : [2025/1/22 23:24]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:24]
 * @updateRemark : [v1.0]
 */
@EqualsAndHashCode(callSuper = true)
@TableName("tb_sys_user")
@Data
public class SysUser extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    private String userAccount;

    private String password;

    private String nickName;
}
