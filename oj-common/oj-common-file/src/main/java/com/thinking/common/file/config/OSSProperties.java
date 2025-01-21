package com.thinking.common.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : OSSProperties
 * @description : [OSS配置属性类]
 * @createTime : [2025/1/21 18:39]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/21 18:39]
 * @updateRemark : [v1.0]
 */
@Data
@Component
@ConfigurationProperties(prefix = "file.oss")
public class OSSProperties {

    private String endpoint;         //节点地址

    private String region;           //地域节点

    private String accessKeyId;      //密钥id

    private String accessKeySecret;  //密钥

    private String bucketName;       //桶名称

    private String pathPrefix;       //路径前缀，加在endPoint之后

}
