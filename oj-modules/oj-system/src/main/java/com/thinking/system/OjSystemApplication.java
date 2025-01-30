package com.thinking.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thinking.system.mapper")
public class OjSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjSystemApplication.class, args);
    }
}
