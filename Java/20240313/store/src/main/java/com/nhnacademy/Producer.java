package com.nhnacademy;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable {

    Mart mart;
    String name;
    List<Store> storeList;
    Random random;

    public Producer(String name, Mart mart) {
        this.name = name;
        this.mart = mart;
    }

    @Override
    public void run() {
        storeList = mart.getStoreList();

        for(Store store : storeList) {
            try {
                store.enter();
                store.buy();
                store.exit();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }

        System.out.println(name + " Producer 종료");
    }
}
