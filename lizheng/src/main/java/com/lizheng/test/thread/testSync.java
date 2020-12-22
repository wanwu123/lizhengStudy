package com.lizheng.test.thread;

public class testSync {
    public  static synchronized void test() throws Exception{
        System.out.println(1);
        Thread.sleep(10000);

    }

    public static void main(String[] args) {
        testSync test1 = new testSync();
        testSync test2 = new testSync();
        new Thread(()-> {
            try {
                testSync.test();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"1").start();
        new Thread(()-> {
            try {
                testSync.test();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"2").start();
    }
}
class testS extends testSync{

}