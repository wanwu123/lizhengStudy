package com.lizheng.test.thread;

public class TestDeadLock {

    private static String a = "a";

    private static String b = "b";

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a){
                System.out.println("threadA进入a同步代码块，执行中");
                try {
                    Thread.sleep(2000);
                    synchronized (b) {
                        System.out.println("threadA进入b");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"AAA").start();
        new Thread(()->{
            synchronized (b){
                System.out.println("threadA进入a同步代码块，执行中");
                try {
                    Thread.sleep(2000);
                    synchronized (a) {
                        System.out.println("threadA进入b");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"BBB").start();
    }
}
