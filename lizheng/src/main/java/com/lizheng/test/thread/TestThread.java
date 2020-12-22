package com.lizheng.test.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {
    private volatile int sa = 0;
    private static int num = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(()->{
            while (true){
                System.out.println(++num);
                //清除中断标志位
                //Thread.interrupted()
                //LockSupport
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("out");
//                    break;
                }
            }
        });
        t1.start();
        t1.interrupt();
    }


    @Test
    public void testSout(){
        String a = "172312451248765";
        int length = a.length()-1;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger atomicIntegerIndex = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            int andIncrement = atomicInteger.getAndIncrement();
            Thread thread = new Thread(() -> {
                while (true) {
                    synchronized (TestThread.class) {
                        int temp = andIncrement;
                        int index = atomicIntegerIndex.get();
                        if (index > length) {
                            sa = 1;
                            return;
                        }
                        int parseInt = Integer.parseInt(String.valueOf(a.charAt(index)));
                        if (temp == parseInt) {
                            System.out.println(temp + "=====" + parseInt);
                            atomicIntegerIndex.getAndIncrement();
                        }
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        while (sa == 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }
}
