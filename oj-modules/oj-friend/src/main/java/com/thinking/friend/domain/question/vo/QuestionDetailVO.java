package com.thinking.friend.domain.question.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionDetailVO
 * @description : [描述说明该类的功能]
 * @createTime : [2025/2/2 23:18]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 23:18]
 * @updateRemark : [描述说明本次修改内容]
 */
@Getter
@Setter
public class QuestionDetailVO extends QuestionVO {

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String defaultCode;

}