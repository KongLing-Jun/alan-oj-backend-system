package com.thinking.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : BaseEntity
 * @description : [时间生成实体基类]
 * @createTime : [2025/1/20 17:39]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 17:39]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class BaseEntity {

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;   //创建人

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;  //创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;    //更新人

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;  //更新时间
}
