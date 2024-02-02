package com.company.MTPPMasterclass.ThreadManagement;

public class ThreadGroupsPlay {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup newGroup = new ThreadGroup("New group");
        ThreadGroup childGroup = new ThreadGroup(newGroup, "Child group");
        System.out.println(childGroup.getParent().getName());
        System.out.println(newGroup.getParent().getName());

        Thread newThread0 = new Thread(newGroup, new MyThread(), "New thread 0");
        Thread newThread1 = new Thread(newGroup, new MyThread(), "New thread 1");

        ThreadGroup parentGroup = newGroup.getParent();
        System.out.println(parentGroup.getName() + " " + parentGroup.getMaxPriority());
        newGroup.setMaxPriority(7);
        newThread1.setPriority(Thread.MAX_PRIORITY);

        newThread0.start();
        newThread1.start();
        System.out.println("New thread 1 priority - " + newThread1.getPriority());
        Thread.sleep(2000);
        newGroup.interrupt();
    }

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
    }
}
