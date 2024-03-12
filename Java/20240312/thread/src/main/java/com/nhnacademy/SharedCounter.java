package com.nhnacademy;

public class SharedCounter extends Thread {
    private SharedCount sharedCount;
    private int count;
    private int maxCount;

    public SharedCounter(String name, int maxCount, SharedCount sharedCount) {
        setName(name);
        this.sharedCount = sharedCount;
        this.maxCount = maxCount;
        count = 0;
    }

    @Override
    public void run() {
        while(count < maxCount) {
            count++;
            sharedCount.increment();
        }
    }
}
