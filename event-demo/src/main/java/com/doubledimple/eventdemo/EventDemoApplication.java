package com.doubledimple.eventdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventDemoApplication.class, args);
    }

}
