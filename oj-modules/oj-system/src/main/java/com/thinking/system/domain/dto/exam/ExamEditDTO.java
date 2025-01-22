package com.thinking.system.domain.dto.exam;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamEditDTO
 * @description : [考试编辑DTO]
 * @createTime : [2025/1/22 23:33]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:33]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class ExamEditDTO extends ExamAddDTO {

    private Long examId;
}
