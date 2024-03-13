package com.nhnacademy;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    static final int MIN_TIME = 100;
    static final int MAX_TIME = 1000;
    List<StoreItem> shoppingList;
    StoreItem[] storeItems;
    Store store;
    String name;

    public Consumer(String name, Store store) {
        this.name = name;
        this.store = store;
        storeItems = StoreItem.values();

        initShoppingList();
    }

    private void initShoppingList() {
        for(int i = 0; i < random.nextInt(storeItems.length); i++) {
            shoppingList.add(storeItems[random.nextInt(storeItems.length)]);
        }
    }

    @Override
    public void run() {
        try {
            store.enter();
            store.sell(shoppingList);
            store.exit();
            Thread.sleep(ThreadLocalRandom.current().nextInt(MIN_TIME, MAX_TIME));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread Interrupted");
        }
    }
}
