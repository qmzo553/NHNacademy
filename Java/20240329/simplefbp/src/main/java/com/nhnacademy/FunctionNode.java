package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FunctionNode extends ActiveNode implements Producer, Consumer, Runnable {

    List<Pipe> inputPipeList;
    Pipe outputPipe;
    List<Message> messageList;
    String function;
    Thread thread;

    Logger log;

    public FunctionNode(String name, String identifier, String function) {
        super(name, identifier);
        this.function = function;
        initialize();
    }

    public void setInputPipe(Pipe pipe) {
        inputPipeList.add(pipe);
    }

    public void setOutputPipe(Pipe pipe) {
        this.outputPipe = pipe;
    }

    public List<Pipe> getInputPipeList() {
        return inputPipeList;
    }

    public Pipe getOutputPipe() {
        return outputPipe;
    }

    @Override
    public void start() {
        log.info("FunctionNode({}) start", getName());
        thread.start();
    }

    @Override
    void initialize() {
        log = LogManager.getLogger();
        inputPipeList = new LinkedList<>();
        messageList = new LinkedList<>();
        thread = new Thread(this);
    }

    @Override
    void perform() {
        if (!messageList.isEmpty()) {
            startPerform();
            terminated();
        }
    }

    private void startPerform() {
        Message newMessage = null;
            int num = 0;
            if (function.equals("+")) {
                for (Message message : messageList) {
                    num += (Integer) message.getMessage();
                }
            } else if (function.equals("-")) {
                for (Message message : messageList) {
                    num -= (Integer) message.getMessage();
                }
            } else if (function.equals("*")) {
                for (Message message : messageList) {
                    num *= (Integer) message.getMessage();
                }
            } else if (function.equals("/")) {
                for (Message message : messageList) {
                    num /= (Integer) message.getMessage();
                }
            }

            newMessage = new NumberMessage(num, num + "");
            outputPipe.send(newMessage);
    }

    @Override
    void idle() {
        for (Pipe pipe : inputPipeList) {
            while (!pipe.isEmptyMessageList()) {
                Message message = pipe.receive();
                synchronized (messageList) {
                    messageList.add(message);
                }
            }
        }
    }

    @Override
    void finalizing() {

    }

    @Override
    void terminated() {
        log.info("FunctionNode({}) stop", getName());
        thread.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                idle();
                
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            perform();
        }
    }
}
