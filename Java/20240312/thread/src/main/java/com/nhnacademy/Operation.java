package com.nhnacademy;

public class Operation implements Runnable {

    private LivelockExample livelockExample;
    
    public Operation(LivelockExample livelockExample) {
        this.livelockExample = livelockExample;
    }

    public void run() {
        livelockExample.operation1();
    }
}
