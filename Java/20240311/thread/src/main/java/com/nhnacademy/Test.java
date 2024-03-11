package com.nhnacademy;

import java.time.LocalTime;

public class Test {
    
    public static void main(String[] args) {

        // Counter counter = new Counter("counter1", 10);
        // ThreadCounter threadCounter = new ThreadCounter("Thread Counter1", 10);
        // ThreadCounter threadCounter2 = new ThreadCounter("Thread Counter2", 10);
        RunnableCounter counter1 = new RunnableCounter("Runnable Counter1", 10);
        RunnableCounter counter2 = new RunnableCounter("Runnable Counter2", 10);
        // Thread thread1 = new Thread(counter1);
        // Thread thread2 = new Thread(counter2);

        System.out.println("start : " + LocalTime.now());
        // counter.run();
        // threadCounter.start();
        // threadCounter2.start();
        // thread1.start();
        // thread2.start();
        counter1.start();
        counter2.start();

        while(counter1.isAlive() || counter2.isAlive()) {
            if((counter1.getCount() > 5) && (counter2.getCount() > 5)) {
                counter1.stop();
                counter2.stop();
            }
        }
        System.out.println("end : " + LocalTime.now());
    }
}
