package com.company.multithreading.threadPoolExample;

public class WorkerThread implements Runnable {
    private String command;

    public WorkerThread(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starts. Command - " + this.command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " ends.");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
