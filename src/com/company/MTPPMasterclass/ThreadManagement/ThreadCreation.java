package com.company.MTPPMasterclass.ThreadManagement;

public class ThreadCreation {
    public static void main(String[] args) throws InterruptedException {
        //MyThread myThread = new MyThread();
        //myThread.start();
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };*/
        System.out.println(Thread.currentThread().getName());
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(runnable);
        thread.setName("Run Thread");
        thread.start();
        thread.join();
        System.out.println(Thread.currentThread().getName());
    }

    static class MyThread extends Thread {
        public void run() {
            setName("New thread");
            System.out.println(Thread.currentThread().getName());
        }
    }
}
