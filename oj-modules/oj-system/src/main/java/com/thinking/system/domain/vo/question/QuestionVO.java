package com.thinking.system.domain.vo.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionVO
 * @description : [题目VO]
 * @createTime : [2025/1/22 23:47]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:47]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
//减少生成的 JSON 数据的大小，避免传输或存储不必要的 null 值
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long questionId;

    private String title;

    private Integer difficulty;

    private String createName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}