package com.lizheng.event;

import com.lizheng.bean.po.User;

public class Event extends WebEvent {
    private User user;
    private int time;

    public Event(Object source, User info, int time) {
        super(source);
        this.user = info;
        this.time = time;
    }

    public void getWeb(){
        System.out.println(1);
    }
}
