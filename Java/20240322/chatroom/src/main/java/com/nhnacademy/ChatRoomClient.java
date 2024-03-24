package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONObject;

public class ChatRoomClient implements Runnable {
    private static int initMessageId = 0;
    int port;
    int id;

    public ChatRoomClient(int port) {
        this.port = port;
    }

    private static int getMessageId() {
        return ++initMessageId;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", port);
            ChatRoomHandler chatRoomHandler = new ChatRoomHandler(socket);
            Thread thread = new Thread(chatRoomHandler);
            thread.start();

            Thread inputAgent = new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    JSONObject request;
                    while ((line = input.readLine()) != null) {
                        request = stringToJson(line);
                        chatRoomHandler.send(request + "\n");
                        System.out.println(request);
                    }
                } catch (IOException e) {
                    //
                }
            });

            Thread outputAgent = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!chatRoomHandler.isEmptyReceiveQueue()) {
                        String line = chatRoomHandler.receive();
                        System.out.println(line);
                    }
                }
            });

            inputAgent.start();
            outputAgent.start();
            System.out.println("서버에 접속 했습니다.");
            thread.join();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.err.println(e.getMessage());
        }
    }

    private JSONObject stringToJson(String request) {
        JSONObject requestJ = new JSONObject();
        String[] tokens = request.split(" ");
        int messageId = getMessageId();
        String type = tokens[0];

        if(type.equals("connect")) {
            String clientId = tokens[1];

            requestJ.put("ID", messageId);
            requestJ.put("type", type);
            requestJ.put("client_id", clientId);
        } else if(type.equals("message")) {
            String clientId = tokens[1];

            requestJ.put("ID", messageId);
            requestJ.put("type", type);
            requestJ.put("target_id", clientId);
            requestJ.put("message", tokens[2]);
        } else if(type.equals("client_list")) {
            requestJ.put("ID", messageId);
            requestJ.put("type", "client_list");
        }

        return requestJ;
    }

}
