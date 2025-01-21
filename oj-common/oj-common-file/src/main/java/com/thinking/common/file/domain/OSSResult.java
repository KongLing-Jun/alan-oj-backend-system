package com.thinking.common.file.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : OSSResult
 * @description : [OSS上传结果封装类]
 * @createTime : [2025/1/21 18:36]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/21 18:36]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class OSSResult {

    private String name;
    //对象状态：true成功，false失败
    private boolean success;
}
