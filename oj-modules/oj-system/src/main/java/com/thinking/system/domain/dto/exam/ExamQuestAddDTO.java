package com.thinking.system.domain.dto.exam;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamQuestAddDTO
 * @description : [考试题目添加DTO]
 * @createTime : [2025/1/22 23:35]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:35]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class ExamQuestAddDTO {

    private Long examId;

    private LinkedHashSet<Long> questionIdSet;
}
