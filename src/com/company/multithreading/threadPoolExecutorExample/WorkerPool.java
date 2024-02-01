package com.company.multithreading.threadPoolExecutorExample;

import com.company.multithreading.threadPoolExample.WorkerThread;

import java.util.concurrent.*;

public class WorkerPool {
    public static void main(String[] args) {
        RejectedExecutionHandlerImpl executionHandler = new RejectedExecutionHandlerImpl();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                threadFactory,
                executionHandler);
        MyMonitorThread myMonitorThread = new MyMonitorThread(threadPoolExecutor, 3);
        Thread monitorThread = new Thread(myMonitorThread);
        monitorThread.start();
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new WorkerThread("cmd " + i));
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myMonitorThread.shutDown();
    }
}
