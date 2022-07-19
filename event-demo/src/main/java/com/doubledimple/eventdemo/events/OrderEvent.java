package com.doubledimple.eventdemo.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @author doubleDimple
 * @date 2022:07:19日 20:25
 */
public class OrderEvent extends ApplicationEvent {


    //事件携带的信息
    private String orderId;

    public OrderEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    @Override
    public String toString() {
        return "OrderEvent{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
