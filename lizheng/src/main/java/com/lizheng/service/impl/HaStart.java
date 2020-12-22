package com.lizheng.service.impl;

public class HaStart implements LambdaTest{
    @Override
    public void onStart(UserStart userStart) {
        userStart.onStart(userStart);
    }
}
