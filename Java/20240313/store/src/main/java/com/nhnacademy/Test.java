package com.nhnacademy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    
    static final int CONSUMER_NUM = 10;
    static final int MAX_CONSUMER_NUM = 100;
    static final int PRODUCER_NUM = 5;
    static final int MAX_PRODUCER_NUM = 100;
    public static void main(String[] args) {
        ExecutorService consumerExecutor = Executors.newFixedThreadPool(CONSUMER_NUM);
        ExecutorService producerExecutor = Executors.newFixedThreadPool(PRODUCER_NUM);
        Store store = new Store();
        
        for(int i = 0; i < MAX_CONSUMER_NUM; i++) {
            consumerExecutor.submit(new Consumer(i + "번", store));
        }

        for(int i = 0; i < MAX_PRODUCER_NUM; i++) {
            producerExecutor.submit(new Producer(i + "번", store));
        }

        consumerExecutor.shutdown();
        producerExecutor.shutdown();
        while(!consumerExecutor.isTerminated() && !producerExecutor.isTerminated());

        System.out.println("종료");
    }
}
