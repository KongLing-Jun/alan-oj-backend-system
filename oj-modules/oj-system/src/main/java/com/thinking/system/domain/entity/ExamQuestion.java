package com.thinking.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thinking.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamQuestion
 * @description : [考试题目实体类]
 * @createTime : [2025/1/22 23:29]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:29]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
@TableName("tb_exam_question")
public class ExamQuestion extends BaseEntity {

    @TableId(value = "EXAM_QUESTION_ID", type = IdType.ASSIGN_ID)
    private Long examQuestionId;

    private Long examId;

    private Long questionId;

    private Integer questionOrder;
}
