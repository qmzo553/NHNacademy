package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    static final int MIN_TIME = 100;
    static final int MAX_TIME = 1000;

    List<StoreItem> deliveryItems;
    StoreItem[] storeItems;
    Store store;
    String name;
    Random random;

    public Producer(String name, Store store) {
        this.name = name;
        this.store = store;
        random = new Random();
        storeItems = StoreItem.values();
        deliveryItems = new LinkedList<>();

        initDeliveryItems();
    }

    private void initDeliveryItems() {
        for (int i = 0; i < random.nextInt(storeItems.length); i++) {
            deliveryItems.add(storeItems[random.nextInt(storeItems.length)]);
        }
    }

    @Override
    public void run() {
        try {
            store.buy(deliveryItems);
            Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_TIME, MAX_TIME));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread Interrupted");
        }
    }
}
