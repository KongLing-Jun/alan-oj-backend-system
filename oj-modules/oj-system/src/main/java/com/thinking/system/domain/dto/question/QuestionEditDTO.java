package com.thinking.system.domain.dto.question;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionEditDTO
 * @description : [题目编辑DTO]
 * @createTime : [2025/1/22 23:37]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:37]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class QuestionEditDTO extends QuestionAddDTO{

    private Long questionId;
}
