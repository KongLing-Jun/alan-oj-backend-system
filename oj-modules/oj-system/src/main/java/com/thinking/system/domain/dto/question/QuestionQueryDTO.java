package com.thinking.system.domain.dto.question;

import com.thinking.common.core.domain.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionQueryDTO
 * @description : [题目查询DTO]
 * @createTime : [2025/1/22 23:38]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:38]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class QuestionQueryDTO extends PageQueryDTO {

    private Integer difficulty;

    private String title;

    private String excludeIdStr;       //  ;

    private Set<Long> excludeIdSet;
}
