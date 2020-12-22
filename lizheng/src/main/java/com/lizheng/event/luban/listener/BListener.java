package com.lizheng.event.luban.listener;


import com.lizheng.event.luban.evnt.BEvent;
import org.springframework.context.event.EventListener;

public class BListener{
    @EventListener
    public void onEvnt(BEvent bEvent) {
        System.out.println("监听到了B事件");
    }
}
