package com.doubledimple.eventdemo.service.impl;

import com.doubledimple.eventdemo.events.MsgEvent;
import com.doubledimple.eventdemo.events.OrderEvent;
import com.doubledimple.eventdemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author doubleDimple
 * @date 2022:07:19日 20:47
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ApplicationContext applicationContext;

    @Override
    public void addOrder(String orderId) {
        long start = System.currentTimeMillis();

        //模拟事件同步处理
        applicationContext.publishEvent(new OrderEvent(this,orderId));

        // 3.短信通知（异步处理）
        applicationContext.publishEvent(new MsgEvent(orderId));

        long end = System.currentTimeMillis();

        log.info("任务全部完成，总耗时：({})毫秒", end - start);

        log.info("订单:[{}]下单成功",orderId);
    }
}
