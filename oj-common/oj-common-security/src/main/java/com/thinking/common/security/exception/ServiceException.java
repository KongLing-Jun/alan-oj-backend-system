package com.thinking.common.security.exception;

import com.thinking.common.core.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ServiceException
 * @description : [服务异常类]
 * @createTime : [2025/1/20 19:22]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 19:22]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {

    private ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }
}
