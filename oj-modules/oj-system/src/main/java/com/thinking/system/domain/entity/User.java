package com.thinking.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.thinking.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : User
 * @description : [用户信息实体类]
 * @createTime : [2025/1/22 23:14]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:14]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
@TableName("tb_user")
public class User extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "USER_ID", type = IdType.ASSIGN_ID)
    private Long id;

    private String nickName;

    private String headImage;

    private Integer sex;

    private String phone;

    private String code;

    private String email;

    private String wechat;

    private String schoolName;

    private String majorName;

    private String introduce;

    private Integer status;
}
