package com.nhnacademy;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    MartItem martItem;
    Store store;
    Thread thread;
    Random random;

    public Producer(Store store) {
        this.store = store;
        random = new Random();
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    // TODO : enum 활용하기
    @Override
    public void run() {
        String[] martItems = {
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10"
        };

        while(thread.getState() != Thread.State.WAITING) {
            store.buy(martItems[random.nextInt(martItems.length)]);
            
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
    }
}
