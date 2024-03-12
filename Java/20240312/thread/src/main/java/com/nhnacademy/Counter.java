package com.nhnacademy;

public class Counter {
    
    private int count = 0;
    private int maxCount = 0;
    private String name;

    public Counter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }
    
    public void run() {
        while (!Thread.currentThread().isInterrupted() && this.count < this.maxCount) {
            this.count++;
            System.out.println(this.name + " : " + this.count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
