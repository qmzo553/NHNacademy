package com.nhnacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TerminalOutNode extends ActiveNode implements Consumer, Runnable {

    Pipe inputPipe;
    Thread thread;

    Logger log;

    public TerminalOutNode(String name, String identifier) {
        super(name, identifier);
        initialize();
    }

    public void setInputPipe(Pipe pipe) {
        this.inputPipe = pipe;
    }

    @Override
    public void start() {
        log.info("TerminalOutNode({}) start", getName());
        thread.start();
    }

    @Override
    void initialize() {
        log = LogManager.getLogger();
        thread = new Thread(this);
    }

    @Override
    void perform() {
        while (inputPipe.isEmptyMessageList()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Message message = inputPipe.receive(); 
        System.out.println(message);
    }

    @Override
    void idle() {

    }

    @Override
    void finalizing() {

    }

    @Override
    void terminated() {
        log.info("TerminalOutNode({}) stop", getName());
        thread.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            perform();
        }
    }
}
