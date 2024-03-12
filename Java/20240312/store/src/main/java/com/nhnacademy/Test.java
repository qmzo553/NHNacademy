package com.nhnacademy;

public class Test {
    
    static final int CONSUMER_NUM = 10;
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer[] consumers = new Consumer[CONSUMER_NUM];

        producer.start();

        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(i + "번", store);

            consumers[i].start();
        }
    }
}
