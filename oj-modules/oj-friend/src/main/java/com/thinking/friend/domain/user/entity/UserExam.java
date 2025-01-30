package com.thinking.friend.domain.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.thinking.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserExam
 * @description : [用户考试实体类]
 * @createTime : [2025/2/2 22:03]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 22:03]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
@TableName("tb_user_exam")
public class UserExam extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "USER_EXAM_ID", type = IdType.ASSIGN_ID)
    private Long userExamId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long examId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private Integer score;

    private Integer examRank;
}