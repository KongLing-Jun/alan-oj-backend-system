package com.thinking.system.domain.dto.user;

import com.thinking.common.core.domain.PageQueryDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserQueryDTO
 * @description : [用户查询DTO]
 * @createTime : [2025/1/22 23:40]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:40]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class UserQueryDTO extends PageQueryDTO {

    private Long userId;

    private String nickName;
}