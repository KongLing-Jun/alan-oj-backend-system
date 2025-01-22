package com.thinking.system.domain.dto.exam;

import com.thinking.common.core.domain.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamQueryDTO
 * @description : [考试查询DTO]
 * @createTime : [2025/1/22 23:34]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:34]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class ExamQueryDTO extends PageQueryDTO {

    private String title;

    private String startTime;

    private String endTime;
}
