package com.thinking.common.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : SwaggerConfig
 * @description : [swagger配置类]
 * @createTime : [2025/1/19 13:38]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/19 13:38]
 * @updateRemark : [v1.0]
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("在线oj系统")
                        .description("在线oj系统接口文档")
                        .version("v1"));
    }
}
