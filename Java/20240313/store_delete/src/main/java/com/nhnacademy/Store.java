package com.nhnacademy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Store {

    static final int WAIT_TIME = 500;
    int consumerNum = 0;
    Map<StoreItem, Integer> sellItems;
    Random random;

    public Store() {
        sellItems = new HashMap<>();
        random = new Random();

        initStoreItems();
    }

    private void initStoreItems() {
        for(int i = 0; i < StoreItem.values().length; i++) {
            StoreItem storeItem = StoreItem.values()[i];
            sellItems.put(storeItem, 0);
        }
    }

    public int getConsumerNum() {
        return this.consumerNum;
    }

    // public synchronized void enter() throws InterruptedException {
    //     while (consumerNum >= MAX_CONSUMER) {
    //         System.out.println(Thread.currentThread().getName() + " 대기 중...");
    //         wait();
    //     }
    //     consumerNum++;
    //     System.out.println(Thread.currentThread().getName() + " 입장");
    // }

    // public synchronized void exit() {
    //     consumerNum--;
    //     System.out.println(Thread.currentThread().getName() + " 퇴장");
    //     notifyAll();
    // }

    public synchronized void buy(List<StoreItem> deliveryItems) {
        while (checkDeliveryItemQuantity(deliveryItems)) {
            try {
                wait(WAIT_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }

        for(StoreItem storeItem : deliveryItems) {
            sellItems.put(storeItem, sellItems.get(storeItem) + 1);
            System.out.println(storeItem.getItemName() + "을 납품 받았습니다.");
        }
        
        notifyAll();
    }

    private boolean checkDeliveryItemQuantity(List<StoreItem> deliveryItems) {
        boolean flag = false;
        for(StoreItem storeItem : deliveryItems) {
            if(sellItems.get(storeItem) == storeItem.getMaxItemNum()) {
                flag = true;
                return flag;
            } else {
                flag = false;
            }
        }

        return flag;
    }

    public synchronized void sell(List<StoreItem> shoppingList) {
        while (checkShoppingItemQuantity(shoppingList)) {
            try {
                wait(WAIT_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }

        for(StoreItem storeItem : shoppingList) {
            sellItems.put(storeItem, sellItems.get(storeItem) - 1);
            System.out.println(Thread.currentThread().getName() + "이 " + storeItem.getItemName() + "를 구매했습니다.");
        }
        
        notifyAll();
    }

    private boolean checkShoppingItemQuantity(List<StoreItem> shoppingList) {
        boolean flag = false;
        for(StoreItem storeItem : shoppingList) {
            if(sellItems.get(storeItem) == 0) {
                flag = true;
                return flag;
            } else {
                flag = false;
            }
        }

        return flag;
    }
}
