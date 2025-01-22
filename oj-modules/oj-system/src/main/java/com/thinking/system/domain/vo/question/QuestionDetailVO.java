package com.thinking.system.domain.vo.question;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionDetailVO
 * @description : [题目详情VO]
 * @createTime : [2025/1/22 23:48]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:48]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class QuestionDetailVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long questionId;

    private String title;

    private Integer difficulty;

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFuc;
}