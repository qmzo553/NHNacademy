package com.nhnacademy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i = 0; i < 10; i++) {
            executorService.submit(new Woker("Woker" + i));
        }

        executorService.shutdown();
        while(!executorService.isTerminated()) {
        }

        System.out.println("모든 작업이 완료되었습니다.");
    }
}
