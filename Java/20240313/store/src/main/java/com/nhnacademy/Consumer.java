package com.nhnacademy;

import java.util.List;

public class Consumer implements Runnable {

    String name;
    Mart mart;
    List<Store> storeList;

    public Consumer(String name, Mart mart) {
        this.name = name;
        this.mart = mart;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        storeList = mart.getStoreList();

        for(Store store : storeList) {
            try {
                store.enter();
                store.sell();
                store.exit();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    }
}
