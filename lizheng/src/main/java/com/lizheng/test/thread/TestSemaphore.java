package com.lizheng.test.thread;

import java.util.concurrent.Semaphore;

public class TestSemaphore {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t"+"start");
                    semaphore.acquire();
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+"\t"+"end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
