package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Store {

    int consumerNum = 0;
    List<String> martItems;
    private final Object lock = new Object(); // 동기화를 위한 객체
    private final int MAX_CONSUMER = 5;
    Random random;

    public Store() {
        martItems = new LinkedList<>();
        random = new Random();
    }

    public int getConsumerNum() {
        return this.consumerNum;
    }

    public void enter() throws InterruptedException {
        synchronized (lock) {
            while (consumerNum > MAX_CONSUMER) {
                // 최대 입장자수를 초과하면 대기
                System.out.println(Thread.currentThread().getName() + " 대기 중...");
                lock.wait();
            }
            consumerNum++;
            System.out.println(Thread.currentThread().getName() + " 입장");
        }
    }

    public void exit() {
        synchronized (lock) {
            consumerNum--;
            System.out.println(Thread.currentThread().getName() + " 퇴장");
            lock.notifyAll(); // 대기 중인 스레드에게 입장할 수 있도록 알림
        }
    }

    private boolean checkMartItemQuantity() {
        return martItems.size() == 10;
    }

    private boolean checkConsumerNum() {
        return consumerNum >= 5;
    }

    public synchronized void buy(String martItem) {
        while (checkMartItemQuantity()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    
        martItems.add(martItem);
        System.out.println(martItem + "을 납품 받았습니다.");
        notifyAll();
    }
    
    public synchronized void sell() {
        while (!checkMartItemQuantity()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    
        String sellMartItem = martItems.get(random.nextInt(martItems.size()));
        martItems.remove(sellMartItem);
        System.out.println(Thread.currentThread().getName() + "이 " + sellMartItem + "를 구매했습니다.");
        notifyAll();
    }
}
