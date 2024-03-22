package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatRoomClient implements Runnable {

    int port;
    int id;
    String clientId;

    public ChatRoomClient(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", port);
            ChatRoomClientHandler chatRoomClientHandler = new ChatRoomClientHandler(socket);
            Thread thread = new Thread(chatRoomClientHandler);
            thread.start();

            Thread inputAgent = new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    while ((line = input.readLine()) != null) {
                        chatRoomClientHandler.send(line + "\n");
                    }
                } catch (IOException e) {
                }
            });

            Thread outputAgent = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!chatRoomClientHandler.isEmptyReceiveQueue()) {
                        String line = chatRoomClientHandler.receive();
                        System.out.println(line);
                    }
                }
            });

            inputAgent.start();
            outputAgent.start();

            System.out.println("서버에 접속 하였습니다.");
            thread.join();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
