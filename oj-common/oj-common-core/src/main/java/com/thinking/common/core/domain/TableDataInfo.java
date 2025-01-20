package com.thinking.common.core.domain;

import com.thinking.common.core.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : TableDataInfo
 * @description : [分页数据封装类]
 * @createTime : [2025/1/20 17:42]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 17:42]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class TableDataInfo {

    private long total;      //总记录数

    private List<?> rows;    //列表数据

    private int code;        //消息状态码

    private String message;   //消息内容

    //初始化表格数据对象
    public TableDataInfo() {
    }

    //查不出任何数据时调用方法
    public static TableDataInfo empty() {
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.setCode(ResultCode.SUCCESS.getCode());
        dataInfo.setRows(new ArrayList<>());
        dataInfo.setMessage(ResultCode.SUCCESS.getMessage());
        dataInfo.setTotal(0);
        return dataInfo;
    }

    //查询出数据时调用方法
    public static TableDataInfo of(List<?> list, long total) {
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.setCode(ResultCode.SUCCESS.getCode());
        dataInfo.setRows(list);
        dataInfo.setMessage(ResultCode.SUCCESS.getMessage());
        dataInfo.setTotal(total);
        return dataInfo;
    }
}
