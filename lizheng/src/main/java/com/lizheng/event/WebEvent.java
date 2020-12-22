package com.lizheng.event;

import org.springframework.context.ApplicationEvent;


public class WebEvent extends ApplicationEvent {


    public WebEvent(Object source) {
        super(source);
    }
}
