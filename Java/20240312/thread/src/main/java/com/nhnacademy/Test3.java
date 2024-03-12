package com.nhnacademy;

public class Test3 {

    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver1 = new Thread(new Receiver(data));
        Thread receiver2 = new Thread(new Receiver(data));

        sender.start();
        receiver1.start();
        receiver2.start();
    }
}
