package com.thinking.friend.domain.question.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionVO
 * @description : [描述说明该类的功能]
 * @createTime : [2025/2/2 23:19]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 23:19]
 * @updateRemark : [描述说明本次修改内容]
 */
@Getter
@Setter
public class QuestionVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long questionId;

    private String title;

    private Integer difficulty;
}
