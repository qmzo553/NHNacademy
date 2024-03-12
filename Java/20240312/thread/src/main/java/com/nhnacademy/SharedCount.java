package com.nhnacademy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedCount {
    Lock lock = new ReentrantLock();
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
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
