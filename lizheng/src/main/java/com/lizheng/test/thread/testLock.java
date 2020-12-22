package com.lizheng.test.thread;

import java.util.concurrent.locks.ReentrantLock;

public class testLock {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        }).start();
        new Thread(()-> {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            reentrantLock.unlock();
        }).start();

    }
}
