package com.company.multithreading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>();
        Callable<String> callable = new MyCallable();
        for (int i = 0; i < 100; i++) {
            Future<String> future = executorService.submit(callable);
            list.add(future);
        }
        for (Future<String> future : list) {
            try {
                System.out.println(new Date() + " :: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}