package com.thinking.system.domain.dto.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamAddDTO
 * @description : [考试添加DTO]
 * @createTime : [2025/1/22 23:33]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:33]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class ExamAddDTO {

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
