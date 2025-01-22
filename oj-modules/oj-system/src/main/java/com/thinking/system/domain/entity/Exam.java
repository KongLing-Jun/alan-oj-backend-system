package com.thinking.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thinking.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : Exam
 * @description : [考试实体类]
 * @createTime : [2025/1/22 23:29]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:29]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
@TableName("tb_exam")
public class Exam extends BaseEntity {

    @TableId(value = "EXAM_ID", type = IdType.ASSIGN_ID)
    private Long examId;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer status;
}
