package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Store {

    static final int MAX_CONSUMER = 5;
    static final int MAX_ITEM = 10;
    int consumerNum = 0;
    List<StoreItem> storeItems;
    Random random;

    public Store() {
        storeItems = new LinkedList<>();
        random = new Random();
    }

    public int getConsumerNum() {
        return this.consumerNum;
    }

    // TODO : 동시에 2명이 입장한다면? -> consumerNum이 공유 자원이 되기 때문에 고려해야 됨
    public synchronized void enter() throws InterruptedException {
        while (consumerNum >= MAX_CONSUMER) {
            System.out.println(Thread.currentThread().getName() + " 대기 중...");
            wait();
        }
        consumerNum++;
        System.out.println(Thread.currentThread().getName() + " 입장");
    }

    public synchronized void exit() {
        consumerNum--;
        System.out.println(Thread.currentThread().getName() + " 퇴장");
        notifyAll();
    }

    public synchronized void buy(StoreItem storeItem) {
        while (storeItems.size() == MAX_ITEM) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }

        storeItems.add(storeItem);
        System.out.println(storeItem.getItemName() + "을 납품 받았습니다.");
        notifyAll();
    }

    public synchronized void sell() {
        while (storeItems.size() != MAX_ITEM) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }

        StoreItem sellStoreItem = storeItems.get(random.nextInt(storeItems.size()));
        storeItems.remove(sellStoreItem);
        System.out.println(Thread.currentThread().getName() + "이 " + sellStoreItem + "를 구매했습니다.");
        notifyAll();
    }
}
