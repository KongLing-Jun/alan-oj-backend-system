package com.thinking.friend.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserSubmitDTO
 * @description : [用户提交代码DTO]
 * @createTime : [2025/2/2 22:06]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 22:06]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class UserSubmitDTO {

    private Long examId;         //可选

    private Long questionId;

    private Integer programType;  // (0: java  1:cpp 2: golang)

    private String userCode;
}