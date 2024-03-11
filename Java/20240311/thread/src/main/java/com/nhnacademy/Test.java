package com.nhnacademy;

import java.time.LocalTime;

public class Test {
    
    public static void main(String[] args) {

        // Counter counter = new Counter("counter1", 10);
        ThreadCounter threadCounter = new ThreadCounter("Thread Counter1", 10);
        ThreadCounter threadCounter2 = new ThreadCounter("Thread Counter2", 10);

        System.out.println("start : " + LocalTime.now());
        // counter.run();
        threadCounter.start();
        threadCounter2.start();

        // while(threadCounter.isAlive() || threadCounter2.isAlive()) {
        
        // }

        System.out.println("end : " + LocalTime.now());
    }
}
