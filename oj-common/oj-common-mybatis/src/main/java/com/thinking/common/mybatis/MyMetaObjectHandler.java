package com.thinking.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.thinking.common.core.constants.Constants;
import com.thinking.common.core.utils.ThreadLocalUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : MyMetaObjectHandler
 * @description : [用于自动填充实体类的创建时间和更新时间]
 * @createTime : [2025/1/19 13:45]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/19 13:45]
 * @updateRemark : [v1.0]
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //创建人  获取当前用户用户id  如何获取当前调用接口的用户的id呢？
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createBy", Long.class, ThreadLocalUtil.get(Constants.USER_ID, Long.class));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateBy", Long.class, ThreadLocalUtil.get(Constants.USER_ID, Long.class));
    }
}
