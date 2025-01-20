package com.thinking.common.core.controller;

import com.github.pagehelper.PageInfo;
import com.thinking.common.core.domain.Result;
import com.thinking.common.core.domain.TableDataInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : BaseController
 * @description : [测试结果封装类]
 * @createTime : [2025/1/20 17:49]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 17:49]
 * @updateRemark : [v1.0]
 */
public class BaseController {

    public Result<Void> toResult(int rows) {
        return rows > 0 ? Result.success() : Result.failed();
    }

    public Result<Void> toResult(boolean result) {
        return result ? Result.success() : Result.failed();
    }

    public TableDataInfo getTableDataInfo(List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return TableDataInfo.empty();
        }
        return TableDataInfo.of(list, new PageInfo<>(list).getTotal());
    }
}
