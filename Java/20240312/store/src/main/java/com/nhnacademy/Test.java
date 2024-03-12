package com.nhnacademy;

public class Test {
    
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer[] consumers = new Consumer[10];

        producer.start();

        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(i + "번", store);

            consumers[i].start();
        }
    }
}
