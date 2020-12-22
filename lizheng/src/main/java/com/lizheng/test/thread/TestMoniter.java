package com.lizheng.test.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

public class TestMoniter {
    private static Unsafe UN_SAFE;

    private static int a = 0;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UN_SAFE = (Unsafe) field.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void add1(){
        UN_SAFE.monitorEnter(TestMoniter.class);
        a++;
    }

    public static void add2(){
        a++;
        UN_SAFE.monitorExit(TestMoniter.class);
    }

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    add1();
                    add2();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+"countDown");
            }).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"end");
        System.out.println(a);
    }
}
