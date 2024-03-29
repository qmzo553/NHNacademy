package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ConsoleInNode extends ActiveNode implements Producer, Runnable {

    Pipe outputPipe;
    Thread thread;

    Logger log;

    protected ConsoleInNode(String name, String identifier) {
        super(name, identifier);
        initialize();
    }

    public void setOutputPipe(Pipe pipe) {
        this.outputPipe = pipe;
    }

    @Override
    public void start() {
        log.info("ConsoleInNode({}) start", getName());
        thread.start();
    }

    @Override
    void initialize() {
        log = LogManager.getLogger();
        thread = new Thread(this);
    }

    @Override
    void perform() {
        try {
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String line;
            int numLine;
            Message message = null;

            log.info("ConsoleInNode({}) input", getName());
            System.out.println("ConsoleInNode " + getName() + "의 입력값 : ");
            line = consoleInput.readLine();
            if (!isString(line)) {
                numLine = Integer.parseInt(line);
                message = new Message(numLine, numLine + "");
            } else {
                message = new Message(line, line);
            }

            outputPipe.send(message);
            consoleInput.close();
        } catch (IOException ignore) {
            //
        }
    }

    @Override
    void idle() {

    }

    @Override
    void finalizing() {
        
    }

    @Override
    void terminated() {
        log.info("ConsoleNode({}) stop", name);
        thread.interrupt();
    }

    private boolean isString(String line) {
        try {
            Integer.parseInt(line);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    @Override
    public void run() {
        perform();
        finalizing();
        terminated();
    }
}
