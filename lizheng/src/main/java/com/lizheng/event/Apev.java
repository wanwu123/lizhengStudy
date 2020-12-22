package com.lizheng.event;

import org.springframework.context.ApplicationEvent;

public class Apev extends ApplicationEvent {

    public Apev(Object source) {
        super(source);
    }
}
