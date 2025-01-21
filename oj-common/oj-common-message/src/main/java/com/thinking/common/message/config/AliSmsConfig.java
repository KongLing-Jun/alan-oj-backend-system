package com.thinking.common.message.config;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : AliSmsConfig
 * @description : [阿里sms信息配置类]
 * @createTime : [2025/1/21 14:26]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/21 14:26]
 * @updateRemark : [v1.0]
 */
@Configuration
public class AliSmsConfig {

    @Value("${sms.aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${sms.aliyun.endpoint}")
    private String endpoint;

    @Bean("aliClient")
    public Client client() throws Exception{
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint(endpoint);
        return new Client(config);
    }
}
