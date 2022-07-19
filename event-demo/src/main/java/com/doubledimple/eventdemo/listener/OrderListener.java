package com.doubledimple.eventdemo.listener;

import com.doubledimple.eventdemo.events.OrderEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 定义监听器
 * @author doubleDimple
 * @date 2022:07:19日 20:28
 */
@Component
@Slf4j
public class OrderListener implements ApplicationListener<OrderEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(OrderEvent event) {
        String orderId = event.getOrderId();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(3000);
        stopWatch.stop();
        log.info("订单:[{}]处理共耗时:[{}]毫秒",orderId,stopWatch.getTotalTimeMillis());

    }
}
