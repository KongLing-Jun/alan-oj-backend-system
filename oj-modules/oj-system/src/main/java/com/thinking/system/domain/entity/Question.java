package com.thinking.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thinking.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : Question
 * @description : [题目信息实体类]
 * @createTime : [2025/1/22 23:27]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:27]
 * @updateRemark : [v1.0]
 */
@TableName("tb_question")
@Getter
@Setter
public class Question extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long questionId;

    private String title;

    private Integer difficulty;

    private Long timeLimit;

    private Long spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFuc;
}
