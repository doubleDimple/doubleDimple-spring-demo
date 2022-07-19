package com.doubledimple.eventdemo.listener;

import com.doubledimple.eventdemo.events.MsgEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author doubleDimple
 * @date 2022:07:19日 20:53
 */
@Slf4j
@Component
public class MsgListener {


    @EventListener(MsgEvent.class)
    @SneakyThrows
    @Async
    public void sendMsg(MsgEvent event){
        String orderId = event.getOrderId();
        long start = System.currentTimeMillis();
        log.info("开发发送短信");
        log.info("开发发送邮件");
        Thread.sleep(4000);
        long end = System.currentTimeMillis();
        log.info("{}：发送短信、邮件耗时：({})毫秒", orderId, (end - start));
    }
}
