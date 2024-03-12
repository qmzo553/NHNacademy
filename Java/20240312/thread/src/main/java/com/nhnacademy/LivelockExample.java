package com.nhnacademy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        LivelockExample livelock = new LivelockExample();

        new Thread(livelock::operation1, "T1").start();
        new Thread(livelock::operation2, "T2").start();

    }

    public void operation1() {
        try {
            while (true) {
                lock1.tryLock(50, TimeUnit.MICROSECONDS);
                System.out.println("lock1 acquired, trying to acquire lock2.");
                Thread.sleep(50);

                if (lock2.tryLock(50, TimeUnit.MICROSECONDS)) {
                    System.out.println("lock2 acquired");
                } else {
                    System.out.println("cannot acquire lock2, releasing lock1");
                    lock1.unlock();
                    continue;
                }

                System.out.println("excuting first operation.");
                break;
            }
            lock2.unlock();
            lock1.unlock();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void operation2() {
        try {
            while (true) {
                lock2.tryLock(50, TimeUnit.MICROSECONDS);
                System.out.println("lock2 acquired, trying to acquire lock1.");
                Thread.sleep(50);

                if (lock1.tryLock(50, TimeUnit.MICROSECONDS)) {
                    System.out.println("lock1 acquired");
                } else {
                    System.out.println("cannot acquire lock1, releasing lock2");
                    lock2.unlock();
                    continue;
                }

                System.out.println("excuting first operation.");
                break;
            }
            lock1.unlock();
            lock2.unlock();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}