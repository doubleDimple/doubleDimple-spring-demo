package com.doubleDimple;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true)
public class DynamicApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DynamicApplication.class);
        LOGGER.info("application start success........");
    }
}
