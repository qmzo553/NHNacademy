package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Store {

    int consumerNum = 0;
    List<StoreItem> storeItems;
    Object lock = new Object();
    static final int MAX_CONSUMER = 5;
    Random random;

    public Store() {
        storeItems = new LinkedList<>();
        random = new Random();
    }

    public int getConsumerNum() {
        return this.consumerNum;
    }

    public void enter() throws InterruptedException {
        synchronized (lock) {
            while (consumerNum > MAX_CONSUMER) {
                System.out.println(Thread.currentThread().getName() + " 대기 중...");
                lock.wait();
            }
            consumerNum++;
            System.out.println(Thread.currentThread().getName() + " 입장");
        }
    }

    public void exit() {
        synchronized (lock) {
            consumerNum--;
            System.out.println(Thread.currentThread().getName() + " 퇴장");
            lock.notifyAll();
        }
    }

    private boolean checkMartItemQuantity() {
        return storeItems.size() == 10;
    }

    public synchronized void buy(StoreItem storeItem) {
        while (checkMartItemQuantity()) {
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
        while (!checkMartItemQuantity()) {
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
