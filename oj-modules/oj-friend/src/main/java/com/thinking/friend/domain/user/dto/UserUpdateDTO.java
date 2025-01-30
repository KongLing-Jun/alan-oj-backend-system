package com.thinking.friend.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserUpdateDTO
 * @description : [用户信息更新DTO]
 * @createTime : [2025/2/2 22:06]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 22:06]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class UserUpdateDTO {

    private String headImage;

    private String nickName;

    private Integer sex;

    private String schoolName;

    private String majorName;

    private String phone;

    private String email;

    private String wechat;

    private String introduce;
}
