package com.thinking.common.file.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : OSSConfig
 * @description : [OSS配置类]
 * @createTime : [2025/1/21 18:38]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/21 18:38]
 * @updateRemark : [v1.0]
 */
@Configuration
@Slf4j
public class OSSConfig {

    @Autowired
    private OSSProperties ossProperties;

    public OSS ossClient;

    @PostConstruct
    public void initOSSClient() {

    }

    //用于创建并配置一个OSS客户端并注册到spring容器中，以便在Spring应用的其他部分使用。
    @Bean
    public OSS ossClient() throws ClientException {
        //用于创建一个默认的凭证提供者。
        DefaultCredentialProvider credentialProvider = new DefaultCredentialProvider
                (ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        //创建ClientBuilderConfiguration对象
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        //使用内网endpoint进行上传
        ossClient = OSSClientBuilder.create()
                .endpoint(ossProperties.getEndpoint())
                .credentialsProvider(credentialProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(ossProperties.getRegion())
                .build();
        return ossClient;
    }

    @PreDestroy
    public void closeOSSClient() {
        ossClient.shutdown();
    }
}
