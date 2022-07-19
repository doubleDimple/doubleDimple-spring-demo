package com.doubledimple.eventdemo.events;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author doubleDimple
 * @date 2022:07:19日 20:52
 */
@Data
@AllArgsConstructor
public class MsgEvent {

    /** 该类型事件携带的信息 */
    public String orderId;


}
