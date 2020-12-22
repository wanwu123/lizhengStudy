package com.lizheng.test.collection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

public class StudyList implements Cloneable {
    @Test
    public void test() throws CloneNotSupportedException {
        StudyList studyList = new StudyList();
        StudyList clone = (StudyList)studyList.clone();
        System.out.println(studyList.hashCode());
        System.out.println(clone.hashCode());
        long[] arr = new long[]{11L,22L,33L,44L};
        ArrayList objects = new ArrayList(new HashSet<String>());
        Spliterator spliterator = objects.spliterator();
        List longs = Arrays.asList(arr);
        new CopyOnWriteArrayList();

        System.out.println(longs.size());

        /*8
            解决输出为1的问题
         */
        long[] arr2 = new long[]{1L,4L,3L,3L};

        List list = Arrays.asList(arr2);
        System.out.println(list.get(0));
    }


    @Test
    public void test2() throws InterruptedException {
        System.out.println(1*100_00);
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>();
        PriorityQueue<Integer> objects1 = new PriorityQueue<>(Integer::compareTo);
        new PriorityBlockingQueue();
        long l = System.currentTimeMillis();
        DelayQueue<Message> delayeds = new DelayQueue<>( );
        new Thread(()->{
            while (true){
                try {
                    System.out.println(delayeds.take().deadtime-l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        delayeds.add(new Message(l+5000));
        delayeds.add(new Message(l+1000));
        delayeds.add(new Message(l+2000));
        delayeds.add(new Message(l+10000));
        objects1.offer(2);
        objects1.offer(1);
        objects1.offer(3);
        objects1.offer(5);
        System.out.println(objects1.poll());
        System.out.println(objects1.poll());
        System.out.println(objects1.poll());
        Thread.sleep(100000);
    }

}
class Message implements Delayed{
    public Message(long deadtime) {
        this.deadtime = deadtime;
    }

    long deadtime;
    @Override
    public String toString() {
        return deadtime+"";
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return deadtime-System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(getDelay(TimeUnit.MICROSECONDS)-o.getDelay(TimeUnit.MICROSECONDS));
    }
}