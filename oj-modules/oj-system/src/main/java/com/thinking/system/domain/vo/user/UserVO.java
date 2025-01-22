package com.thinking.system.domain.vo.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserVO
 * @description : [普通用户VO]
 * @createTime : [2025/1/22 23:50]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:50]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class UserVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String nickName;

    private Integer sex;

    private String phone;

    private String email;

    private String wechat;

    private String schoolName;

    private String majorName;

    private String introduce;

    private Integer status;
}
