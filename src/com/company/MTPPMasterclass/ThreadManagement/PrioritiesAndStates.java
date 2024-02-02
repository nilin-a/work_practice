package com.company.MTPPMasterclass.ThreadManagement;

public class PrioritiesAndStates {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());

        Thread maxThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
        });

        Thread minThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority());
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
        });
        minThread.setPriority(Thread.MIN_PRIORITY);

        System.out.println(maxThread.getName() + " " + maxThread.getState());
        System.out.println(minThread.getName() + " " + minThread.getState());

        maxThread.start();
        minThread.start();

        System.out.println(maxThread.getName() + " " + maxThread.getState());
        System.out.println(minThread.getName() + " " + minThread.getState());

        maxThread.join();
        minThread.join();

        System.out.println(maxThread.getName() + " " + maxThread.getState());
        System.out.println(minThread.getName() + " " + minThread.getState());
    }
}
