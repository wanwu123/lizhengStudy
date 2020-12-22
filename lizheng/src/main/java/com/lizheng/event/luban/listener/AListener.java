package com.lizheng.event.luban.listener;

import com.lizheng.event.luban.evnt.AEvent;
import org.springframework.context.event.EventListener;

public class AListener{
    @EventListener
    public void onEvnt(AEvent aEvent) {
        System.out.println("监听到了A事件");
    }
}
