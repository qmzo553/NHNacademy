package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mart {

    static final int CONSUMER_NUM = 5;
    static final int MAX_CONSUMER_NUM = 100;
    static final int PRODUCER_NUM = 10;
    static final int MAX_PRODUCER_NUM = 100;
    static final int STORE_MAXNUM = 5;
    static final int STORE_MINNUM = 1;
    List<Store> storeList;
    Random random;

    public Mart() {
        random = new Random();
        storeList = new LinkedList<>();

        inittStoreList();
    }

    public void start() {
        ExecutorService consumerExecutor = Executors.newFixedThreadPool(CONSUMER_NUM);
        ExecutorService producerExecutor = Executors.newFixedThreadPool(PRODUCER_NUM);

        for (int i = 0; i < MAX_CONSUMER_NUM; i++) {
            consumerExecutor.submit(new Consumer(i + "번 소비자", this));
        }
   
        for (int i = 0; i < MAX_PRODUCER_NUM; i++) {
            producerExecutor.submit(new Producer(i + "번 생산자", this));
        }

        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted");
        }

        consumerExecutor.shutdown();
        producerExecutor.shutdown();
        while (!consumerExecutor.isTerminated() && !producerExecutor.isTerminated());

        System.out.println("종료");
    }

    private void inittStoreList() {
        for(int i = 0; i < STORE_MAXNUM; i++) {
            storeList.add(new Store(i + "번 Store"));
        }
    }

    public List<Store> getStoreList() {
        List<Store> randomList = new LinkedList<>();
        int storeNum = random.nextInt(STORE_MAXNUM - STORE_MINNUM) + STORE_MINNUM;

        for(int i = 0; i < storeNum; i++) {
            Store store = storeList.get(random.nextInt(storeList.size()));
            randomList.add(store);
        }

        return randomList;
    }
    
    
}
