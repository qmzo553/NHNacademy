package com.nhnacademy;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    static final int MIN_TIME = 100;
    static final int MAX_TIME = 1000;

    StoreItem[] storeItems;
    Store store;
    Thread thread;
    Random random;

    public Producer(Store store) {
        this.store = store;
        storeItems = StoreItem.values();
        random = new Random();
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        while(thread.getState() != Thread.State.WAITING) {
            store.buy(storeItems[random.nextInt(storeItems.length)]);
            
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_TIME, MAX_TIME));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }
}
