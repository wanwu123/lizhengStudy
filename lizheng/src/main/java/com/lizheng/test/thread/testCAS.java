package com.lizheng.test.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class testCAS {

    private int i = 0;

    public static Unsafe UNSAFE;

    public static long OFFSET;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            System.out.println(UNSAFE.toString());
            OFFSET = UNSAFE.objectFieldOffset(testCAS.class.getDeclaredField("i"));
            System.out.println(OFFSET);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        final testCAS testCAS = new testCAS();

        new Thread(() -> {
            while (true){

                boolean b = UNSAFE.compareAndSwapInt(testCAS, OFFSET, testCAS.i, testCAS.i + 1);
                if (b){
                    System.out.println(UNSAFE.getIntVolatile(testCAS,OFFSET));
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true){
                boolean b = UNSAFE.compareAndSwapInt(testCAS, OFFSET, testCAS.i, testCAS.i + 1);
                if (b){
                    System.out.println(UNSAFE.getIntVolatile(testCAS,OFFSET));
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
