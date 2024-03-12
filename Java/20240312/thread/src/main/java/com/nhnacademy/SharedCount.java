package com.nhnacademy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedCount {
    Lock lock = new ReentrantLock();
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        // lock.lock();
        // setCount(getCount() + 1);
        // lock.unlock();
        synchronized(this) {
            setCount(getCount() + 1);
        }
    }

    // public void increment2() {
    //     setCount(getCount() + 1);
    // }
}
