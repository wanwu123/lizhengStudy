package com.lizheng.callback;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class CallBackOne implements SmartLifecycle {
    @Override
    public boolean isAutoStartup() {
        System.out.println("isAutoStartup");
        return false;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("stop2");
    }

    @Override
    public int getPhase() {
        System.out.println("getPhase");
        return 0;
    }

    @Override
    public void start() {
        System.out.println("start1");
    }

    @Override
    public void stop() {
        System.out.println("stop1");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
