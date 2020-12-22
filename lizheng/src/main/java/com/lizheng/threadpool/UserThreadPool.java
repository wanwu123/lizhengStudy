package com.lizheng.threadpool;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class UserThreadPool {

    private ThreadPoolExecutor poolExecutor;


    @PostConstruct
    public void init() {
        this.poolExecutor = new ThreadPoolExecutor(8,
                8,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(100)
                , new UserThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void exec(Runnable runnable){
        poolExecutor.execute(runnable);
    }


    class UserThreadFactory implements ThreadFactory{
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        UserThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "UserPool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            t.setDaemon(true);
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
class ThreadTest extends  Thread{
    private String name;

    public ThreadTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }

    public static void main(String[] args) {
        new ThreadTest("a").start();
        new ThreadTest("b").run();
    }
}

class ThreadCall implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "xxx";
    }

    public static void main(String[] args) throws Exception{
        FutureTask<String> stringFutureTask = new FutureTask<>(new ThreadCall(){});
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        thread.join();
        System.out.println(stringFutureTask.get());
    }
}