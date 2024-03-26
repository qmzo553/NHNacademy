package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SimpleCurlClient{
    
    String url;
    int port = 80;
    boolean verbose = false;
    String method = "GET";
    String header;

    public SimpleCurlClient(String url) {
        this.url = url;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void connect() {
        try (Socket socket = new Socket(url, port);) {
            
            Thread inputAgent = new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException ignore) {
                    //
                }
            });

            Thread outputAgent = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        BufferedWriter ouput = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    }
                } catch (IOException ignore) {
                    //
                }
            });

            inputAgent.start();
            outputAgent.start();
        } catch (IOException ignore) {
            //
        }
    }
}
