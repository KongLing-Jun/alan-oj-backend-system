package com.thinking.common.rabbit;

import com.thinking.common.core.constants.RabbitMQConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : RabbitConfig
 * @description : [RabbitMQ配置类]
 * @createTime : [2025/1/21 13:39]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/21 13:39]
 * @updateRemark : [v1.0]
 */
@Configuration
public class RabbitConfig {

    //往spring容器注入消息队列
    @Bean
    public Queue workQueue() {
        return new Queue(RabbitMQConstants.OJ_WORK_QUEUE,true);
    }

    //往spring容器注入消息转换器
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
