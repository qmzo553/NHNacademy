package com.nhnacademy;

public class TimerNode extends ActiveNode implements Producer, Runnable {

    protected TimerNode(String name, String identifier) {
        super(name, identifier);
    }

    @Override
    public void start() {
        
    }

    @Override
    void initialize() {
        
    }

    @Override
    void perform() {
        
    }

    @Override
    void idle() {
        
    }

    @Override
    void finalizing() {
        
    }

    @Override
    void terminated() {
        
    }
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            // 메세지를 출력하는 코드
        }
    }
}
