package com.thinking.system.domain.vo.exam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.thinking.system.domain.vo.question.QuestionVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamDetailVO
 * @description : [考试详情VO]
 * @createTime : [2025/1/22 23:45]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:45]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDetailVO {

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private List<QuestionVO> examQuestionList;
}