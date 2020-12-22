package com.lizheng.service.impl;

import org.apache.catalina.startup.Tomcat;

public class UserStart implements LambdaTest{
    @Override
    public void onStart(UserStart userStart) {
        System.out.println("UserStart start");
    }
}
