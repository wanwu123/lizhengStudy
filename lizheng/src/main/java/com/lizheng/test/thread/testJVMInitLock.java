package com.lizheng.test.thread;

public class testJVMInitLock {
    public static void main(String[] args) {
        new Thread(() -> A.test()).start();
        new Thread(() -> B.test()).start();
    }
}

class A{

        static {
            System.out.println(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new B();
        }
       public static void test(){
           System.out.println(1);
           new B();
       }

}

class B{

    static {
        System.out.println(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new A();
    }

    public static void test(){
        System.out.println(1);
        new A();
    }
}