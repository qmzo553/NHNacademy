package com.nhnacademy;

public class RunnableCounter implements Runnable {

    private int count = 0;
    private int maxCount = 0;
    private String name;
    private Thread thread;

    public RunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        thread = new Thread(this);
    }

    public int getCount() {
        return this.count;
    }

    public Thread getThread() {
        return this.thread;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && this.count < this.maxCount) {
            this.count++;
            System.out.println(this.name + " : " + this.count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        // 이 interrupt는 누가 하는 것일까?
        thread.interrupt();
        // Thread.currentThread().interrupt();
        // System.out.println(Thread.currentThread().getName() + " stop");
    }

    public boolean isAlive() {
        return thread.isAlive();
    }
}
