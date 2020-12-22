package com.lizheng.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

public class Apev2 extends ApplicationEvent {
    public Apev2(Object source) {
        super(source);
    }
}
