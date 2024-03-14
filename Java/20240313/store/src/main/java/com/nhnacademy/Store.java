package com.nhnacademy;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Store {

    static final int MAX_CONSUMER_NUM = 5;
    static final int MAX_BUY_NUM = 3;
    static final int MAX_SELL_NUM = 3;
    static final int MAX_ITEM = 10;
    static final int WAIT_TIME = 5;

    Semaphore storeSemaphore;
    Semaphore buySemaphore;
    Semaphore sellSemaphore;
    Random random;
    String name;
    int cntConsumerNum = 0; // 현재 인원수
    int maxStoreItemNum = 0; // 랜덤 최대 물품 수
    int cntStoreItemNum = 0; // 현재 재고 물품 수

    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public Store(String name) {
        this.name = name;
        random = new Random();
        this.maxStoreItemNum = random.nextInt(MAX_ITEM) + 1;
        storeSemaphore = new Semaphore(MAX_CONSUMER_NUM);
        buySemaphore = new Semaphore(MAX_CONSUMER_NUM);
        sellSemaphore = new Semaphore(MAX_CONSUMER_NUM);
    }

    // semaphore.availablePermits()
    public void enter() throws InterruptedException {
        storeSemaphore.acquire();
        cntConsumerNum++;
        logger.info("({})가 ({})에 입장하였습니다. (인원수 : {}/{}명)", Thread.currentThread().getName(), name, cntConsumerNum, MAX_CONSUMER_NUM);
    }

    public void exit() {
        storeSemaphore.release();
        cntConsumerNum--;
        logger.info("({}) 가 ({})에서 퇴장하였습니다. (인원수 : {}/{}명)", Thread.currentThread().getName(), name, cntConsumerNum, MAX_CONSUMER_NUM);
    }

    public void buy() throws InterruptedException {
        buySemaphore.acquire();
        if (cntStoreItemNum >= maxStoreItemNum || (!buySemaphore.tryAcquire(WAIT_TIME, TimeUnit.SECONDS))) {
            logger.log(Level.WARN, "({})가 ({})에서 납품 포기 했습니다.(시간 초과)", Thread.currentThread().getName(), name);
            return;
        }

        cntStoreItemNum++;
        logger.info("({})가 ({})에 납품 했습니다. (재고량 : {}/{})", 
            Thread.currentThread().getName(), name, cntStoreItemNum, maxStoreItemNum);
        buySemaphore.release();
    }

    public void sell() throws InterruptedException {
        sellSemaphore.acquire();
        if (cntStoreItemNum <= 0 || (!sellSemaphore.tryAcquire(WAIT_TIME, TimeUnit.SECONDS))) {
            logger.log(Level.WARN, "({})가 ({})에서 구매 포기 했습니다.(시간 초과)", Thread.currentThread().getName(), name);
            return;
        }

        cntStoreItemNum--;
        logger.info("({})가 ({})에서 구매 했습니다. (재고량 : {}/{})",
            Thread.currentThread().getName(), name, cntStoreItemNum, maxStoreItemNum);
        sellSemaphore.release();
    }
}
