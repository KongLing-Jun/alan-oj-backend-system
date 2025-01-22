package com.thinking.system.domain.dto.question;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionAddDTO
 * @description : [题目添加DTO]
 * @createTime : [2025/1/22 23:37]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:37]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class QuestionAddDTO {

    private String title;

    private Integer difficulty;

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFuc;
}