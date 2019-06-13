package com.helloxin.zootopia.dog.common.thread;

import java.util.concurrent.*;

/**
 * Created by nandiexin on 2019/6/13.
 */
public class ThreadDogThreadPool {

    /**
     * 主线程数 cpu是4核的目前就这么设置吧
     */
    private int corePoolSize = 4;

    /**
     * 最大线程数
     */
    private int maximumPoolSize = 60;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private long keepAliveTime = 3000;

    /**
     * 线程池所使用的缓冲队列的大小
     */
    private int queueSize = 50;

    /**
     * 单例的线程池类
     */
    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadDogThreadPool() {

        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize), new BlockingQueuePut());

        threadPoolExecutor.allowCoreThreadTimeOut(false);

    }

    private static class BlockingQueuePut implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void addTask(Runnable task) {

        threadPoolExecutor.execute(task);

    }

    public <T> Future<T> addTask(Callable<T> task) {

        return threadPoolExecutor.submit(task);
    }

    public void stop() {

        threadPoolExecutor.shutdownNow();
    }


}
