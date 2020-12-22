package com.lizheng.test.thread;

import sun.misc.Contended;

import java.util.concurrent.ConcurrentHashMap;

public class TestVolitile {
    public static void main(String[] args) {
        Oi oi = new Oi();
        new ConcurrentHashMap<>();
        new Thread(
                () ->{
                    for (int i = 0; i < 100000000; i++) {

                    }
                }
        ).start();
    }

}
class Oi{

    @Contended
    volatile long x;

    long p1,p2,p3,p4,p5,p6,p7;

    volatile long y;
}