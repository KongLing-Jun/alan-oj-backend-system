package com.thinking.common.security.config;

import com.thinking.common.security.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : WebMvcConfig
 * @description : [请求拦截配置类]
 * @createTime : [2025/1/20 19:20]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 19:20]
 * @updateRemark : [v1.0]
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/**/login","/**/test/**")
                .addPathPatterns("/**");
    }
}
