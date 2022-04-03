package com.igniteDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class IgniteApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(IgniteApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IgniteApplication.class);
        LOGGER.info("application start success........");
    }
}
