package com.thinking.friend.domain.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserVO
 * @description : [用户信息VO]
 * @createTime : [2025/2/2 22:05]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 22:05]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class UserVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

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