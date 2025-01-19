package com.thinking.common.core.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : PageQueryDTO
 * @description : [分页查询封装类]
 * @createTime : [2025/1/19 23:57]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/19 23:57]
 * @updateRemark : [描述说明本次修改内容]
 */
@Getter
@Setter
public class PageQueryDTO {

    private Integer pageSize = 10; //每页的数据  必传

    private Integer pageNum = 1;   //第几页   必传
}
