package com.thinking.common.core.domain;

import com.thinking.common.core.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : Result
 * @description : [结果返回封装类]
 * @createTime : [2025/1/19 23:35]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/19 23:35]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class Result<T> {

    private int code;       //定义一些固定的code，前后端商量好的   0  1  请求成功  常量  2  3  枚举

    private String message; //  通常是code的辅助说明  一个code  对应一个message

    private T data;         //请求某个接口返回的数据list  SysUser  泛型

    public static <T> Result<T> success() {
        return assembleResult(null, ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return assembleResult(data, ResultCode.SUCCESS);
    }

    public static <T> Result<T> failed() {
        return assembleResult(null, ResultCode.FAILED);
    }

    public static <T> Result<T> failed(int code, String message) {
        return assembleResult(code, message, null);
    }

    /**
     * 指定错误码
     *
     * @param resultCode 指定错误码
     * @param <T>
     * @return
     */
    public static <T> Result<T> failed(ResultCode resultCode) {
        return assembleResult(null, resultCode);
    }

    private static <T> Result<T> assembleResult(T data, ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    }

    private static <T> Result<T> assembleResult(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
