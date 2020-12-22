package com.lizheng.event;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class Listener {

    @EventListener
    public void onApplicationEvent(WebEvent webEvent) {
        System.out.println("监听到事件6666");
    }
}
