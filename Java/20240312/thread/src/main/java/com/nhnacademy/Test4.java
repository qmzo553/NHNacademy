package com.nhnacademy;

public class Test4 {

    static final long MAX_COUNT = 1_000_000_000;
    
    public static void main(String[] args) throws InterruptedException {
        SharedCount sharedCount = new SharedCount();
        SharedCounter sharedCounter1 = new SharedCounter("T1", MAX_COUNT, sharedCount);
        SharedCounter sharedCounter2 = new SharedCounter("T2", MAX_COUNT, sharedCount);

        System.out.println(sharedCounter1.getState());
        System.out.println(sharedCounter2.getState());

        sharedCounter1.start();
        Thread.sleep(100); 
        sharedCounter2.start();

        while(sharedCounter1.isAlive()){
            System.out.println(sharedCounter2.getState());
            Thread.sleep(100);
        }


        // RunnableCounter runnableCounter = new RunnableCounter("counter", 5);

        // runnableCounter.start();

        // System.out.println(runnableCounter.getThread().getState());
    }
}


