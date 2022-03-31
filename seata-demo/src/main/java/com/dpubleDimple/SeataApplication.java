package com.dpubleDimple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SeataApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SeataApplication.class);
        LOGGER.info("application start success........");
    }
}
