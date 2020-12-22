package com.lizheng.event.luban.listener;


import com.lizheng.event.luban.evnt.AEvent;
import org.springframework.context.event.EventListener;

public class AAListener {
    @EventListener
    public void onEvnt(AEvent aEvent) {
        System.out.println("AA也监听到了");
    }
}
