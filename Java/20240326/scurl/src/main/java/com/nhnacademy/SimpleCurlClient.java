package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SimpleCurlClient {

    String url;
    String host = "50.16.63.240";
    int port = 80;
    boolean verbose = false;
    String method = "GET";
    String header;
    String data;

    public SimpleCurlClient(String url) {
        this.url = url;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void connect() {
        try (Socket socket = new Socket(host, port);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
            
            Thread inputAgent = new Thread(() -> {
                try {
                    while(!Thread.currentThread().isInterrupted()) {
                        String line;
                        while ((line = input.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                    
                } catch (IOException ignore) {
                    //
                }
            });

            Thread outputAgent = new Thread(() -> {
                try {
                    output.write(request());
                    output.flush();
                } catch (IOException ignore) {
                    //
                }
            });

            inputAgent.start();
            outputAgent.start();
            System.out.println("서버에 접속 했습니다.");
        } catch (IOException ignore) {
            //
        }
    }

    private String request() {
        StringBuilder sb = new StringBuilder();

        sb.append(method);
        sb.append(" /" + url);
        sb.append(" HTTP/1.1\n");

        if(header != null) {
            sb.append(header + "\n");
        }

        if(method.equals("POST") && data != null) {
            sb.append("\n");
            sb.append(data + "\n");
        }

        sb.append("Host: " + host + "\n");
        
        System.out.println(sb.toString());
        return sb.toString();
    }
}
