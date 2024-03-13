package com.nhnacademy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        // ExecutorService executorService = Executors.newFixedThreadPool(3);

        // String[] urls = {
        //     "https://nhnacademy.dooray.com/share/drive-files/ocfkrcbb5vui.go2pyBWXRZ6IXN3whxDFtg",
        //     "https://nhnacademy.dooray.com/share/drive-files/ocfkrcbb5vui.YQloTWfJRz24Xhq2aVSGgw",
        //     "https://nhnacademy.dooray.com/share/drive-files/ocfkrcbb5vui.DwdVMtMaTmOFS_mQebo56w",
        //     "https://nhnacademy.dooray.com/share/drive-files/ocfkrcbb5vui.e2pbYnmHT_mRPWZZ3Z511Q",
        //     "https://nhnacademy.dooray.com/share/drive-files/ocfkrcbb5vui.p0sB3Ke2Tt64uXFPa1sU5A"
        // };
    


        // // for(int i = 0; i < 10; i++) {
        // //     executorService.submit(new Woker("Woker" + i));
        // // }

        // for(String url : urls) {
        //     Runnable worker = new DownloadWorker(url);
        //     executorService.execute(worker);
        // }

        // executorService.shutdown();
        // while(!executorService.isTerminated()) {
        //     //
        // }

        // System.out.println("모든 작업이 완료되었습니다.");

        ThreadGroup group = new ThreadGroup("group");

        RunnableCounter thread1 = new RunnableCounter(group, "worker1", 100);
        RunnableCounter thread2 = new RunnableCounter(group, "worker2", 100);

        

        thread1.start();
        thread2.start();

        Thread.sleep(5000);
        group.interrupt();
    }
}
