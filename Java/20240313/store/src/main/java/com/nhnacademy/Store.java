package com.nhnacademy;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Store {

    static final int MAX_PEOPLE = 5;
    static final int MAX_ITEM = 10;
    static final int WAIT_TIME = 1;

    Semaphore storeSemaphore;
    Random random;
    String name;
    int cntPeopleCount = 0;
    int storeItemNum = 0;
    int buyNum = 0;

    public Store(String name) {
        this.name = name;
        random = new Random();
        this.storeItemNum = random.nextInt(MAX_ITEM) + 1;
        storeSemaphore = new Semaphore(MAX_PEOPLE);
    }

    public void enter() throws InterruptedException {
        storeSemaphore.acquire();
        cntPeopleCount++;
        System.out.println(name + "에 입장, 현재 인원수 : " + cntPeopleCount + "명");
    }

    public void exit() {
        storeSemaphore.release();
        cntPeopleCount--;
        System.out.println(name + "에서 퇴장, 현재 인원수 : " + cntPeopleCount + "명");
    }

    public void buy() throws InterruptedException {
        if (buyNum >= storeItemNum && (!storeSemaphore.tryAcquire(WAIT_TIME, TimeUnit.SECONDS))) {
            System.out.println("Producer - 납품 포기(시간초과)");
            return;
        }

        buyNum++;
        System.out.println("납품 받았습니다.");
    }

    public void sell() throws InterruptedException {
        if (buyNum < 0 && (!storeSemaphore.tryAcquire(WAIT_TIME, TimeUnit.SECONDS))) {
            System.out.println("Consumer - 구매 포기(시간초과)");
            return;
        }

        buyNum--;
        System.out.println(Thread.currentThread().getName() + "이 구매했습니다.");
    }
}
