package com.thinking.friend.domain.question.dto;

import com.thinking.common.core.domain.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionQueryDTO
 * @description : [问题查询DTO]
 * @createTime : [2025/2/2 23:15]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 23:15]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class QuestionQueryDTO extends PageQueryDTO {

    private String keyword;

    private Integer difficulty;
}
