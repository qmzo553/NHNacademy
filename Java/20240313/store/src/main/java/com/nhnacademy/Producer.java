package com.nhnacademy;

import java.util.List;

public class Producer implements Runnable {

    Mart mart;
    String name;
    List<Store> storeList;

    public Producer(String name, Mart mart) {
        this.name = name;
        this.mart = mart;
    }

    @Override
    public void run() {
        storeList = mart.getStoreList();

        for(Store store : storeList) {
            try {
                store.buy();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    }
}
