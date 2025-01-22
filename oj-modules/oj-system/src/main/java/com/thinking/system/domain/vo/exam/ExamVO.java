package com.thinking.system.domain.vo.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamVO
 * @description : [考试VO]
 * @createTime : [2025/1/22 23:46]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:46]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class ExamVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long examId;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private Integer status;

    private String createName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}