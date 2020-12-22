package com.lizheng.test.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

public class TestForkJoin {

    private static volatile int status;

    private static long OFFSET;

    private static Unsafe UN_SAFE;

    private static Node node;

    private static long c;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UN_SAFE = (Unsafe) field.get(null);
            OFFSET = UN_SAFE.objectFieldOffset(TestForkJoin.class.getDeclaredField("status"));
            c = UN_SAFE.objectFieldOffset(TestForkJoin.class.getDeclaredField("node"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        //forkJoinPool.execute();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                    if (UN_SAFE.compareAndSwapObject(node,c,null,new Node(null,Thread.currentThread()))){
                        System.out.println(Thread.currentThread().getName()+"setFirstNode");
                    }
                    System.out.println(Thread.currentThread().getName()+"doing");
                    if (UN_SAFE.compareAndSwapInt(status,OFFSET,0,1)){
                        System.out.println(Thread.currentThread().getName()+"get!!!");
                        try {
                            System.out.println(Thread.currentThread().getName()+"sleep");
                            Thread.sleep(3000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        UN_SAFE.compareAndSwapInt(status,OFFSET,1,0);
                        System.out.println(Thread.currentThread().getName()+"checkNode");
                        node = node.next;
                        LockSupport.unpark(node.me);
                    }else {
                        System.out.println(Thread.currentThread().getName()+"park!!!");
                        Node lastNode = getLastNode(node);
                        if (UN_SAFE.compareAndSwapObject(lastNode,c,null,new Node(null,Thread.currentThread()))){
                            System.out.println(Thread.currentThread().getName()+"addNode");
                        }
                        LockSupport.park();
                        Node lastNode2 = getLastNode(node);
                        if (lastNode2 != null){
                            LockSupport.unpark(lastNode2.me);
                        }
                        System.out.println(Thread.currentThread().getName()+"unpark");
                    }
                    System.out.println(Thread.currentThread().getName()+"finish");
            }).start();
        }
    }
    static class Node{
        private Node next;
        private Thread me;

        public Node() {
        }

        public Node( Node next, Thread me) {
            this.next = next;
            this.me = me;
        }
    }

    public static Node getLastNode(Node node){
        Node temp = node;
        while (temp.next != null){
            temp = temp.next;
        }
        return temp;
    }
}
