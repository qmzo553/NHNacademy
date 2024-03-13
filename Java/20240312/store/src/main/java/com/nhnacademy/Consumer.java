package com.nhnacademy;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    static final int MIN_TIME = 100;
    static final int MAX_TIME = 1000;
    Store store;
    Thread thread;

    public Consumer(String name, Store store) {
        this.store = store;
        thread = new Thread(this, name);
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        try {
            store.enter();
            store.sell();
            store.exit();
            Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_TIME, MAX_TIME));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread Interrupted");
        }
    }
}
