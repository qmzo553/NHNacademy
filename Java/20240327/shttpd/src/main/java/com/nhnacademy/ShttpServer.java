package com.nhnacademy;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShttpServer implements Runnable {

    int port;

    Logger log = LogManager.getLogger(this.getClass().getSimpleName());

    public ShttpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        log.trace("Start server : {}", port);
        while (!Thread.currentThread().isInterrupted()) {
            try (ServerSocket serverSocket = new ServerSocket(port);){
                ServiceHandler serviceHandler = new ServiceHandler(serverSocket.accept());
                Thread thread = new Thread(serviceHandler);
                thread.start();
            } catch (IOException ignore) {
                //
            }
        }
        log.trace("Stopped server : {}", port);
    }
}
