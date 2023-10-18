package com.doubleDimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 本地启动redis命令 /usr/local/opt/redis@6.2/bin/redis-server /usr/local/etc/redis.conf
 */
@SpringBootApplication
public class RedssionApplication {


    public static void main(String[] args) {
        SpringApplication.run(RedssionApplication.class,args);
    }
}
