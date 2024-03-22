package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChatRoomServer implements Runnable {
    int port;
    ServerSocket serverSocket;
    List<Thread> clientHandlerList = new LinkedList<>();
    List<ChatRoomClientHandler> chatRoomClientHandlerList = new LinkedList<>();
    List<Integer> denyList = new LinkedList<>();
    Map<Integer, String> userList = new HashMap<>();

    public ChatRoomServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Thread inputAgent = new Thread(() -> {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                String line;

                while ((line = input.readLine()) != null) {
                    if(line.charAt(0) == '/') { 
                        commandHandler(line);
                        continue;
                    }
                    
                    synchronized (chatRoomClientHandlerList) {
                        for (ChatRoomClientHandler chatRoomClient : chatRoomClientHandlerList) {
                            chatRoomClient.send(line);
                        }
                    }
                }
            } catch (IOException e) {
                //
            }
        });

        Thread outputAgent = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (chatRoomClientHandlerList) {
                    for (ChatRoomClientHandler chatRoomClient : chatRoomClientHandlerList) {
                        if (!chatRoomClient.isEmptyReceiveQueue()) {
                            String line = chatRoomClient.receive();
                            System.out.println(line);
            
                            if (line.contains("connect")) {
                                connectionHandler(line);
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        inputAgent.start();
        outputAgent.start();

        try {
            serverSocket = new ServerSocket(port);
            while (!Thread.currentThread().isInterrupted()) {
                ChatRoomClientHandler chatRoomClientHandler = new ChatRoomClientHandler(serverSocket.accept());
                Thread thread = new Thread(chatRoomClientHandler);
                thread.start();
                System.out.println("클라이언트가 접속 하였습니다.");
                clientHandlerList.add(thread);
                synchronized (chatRoomClientHandlerList) {
                    chatRoomClientHandlerList.add(chatRoomClientHandler);
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectionHandler(String request) {
        String[] tokens = request.split(" ");
        int id = Integer.parseInt(tokens[0]);
        String type = tokens[1];
        String clientId = tokens[2];

        if(denyList.contains(tokens[0])) {
            // TODO : 접속 불가 메세지 보내기
        }

        userList.put(id, clientId);
        // TODO : 접속 허용 메세지 보내기
    }

    private void commandHandler(String command) {
        String[] tokens = command.split(" ");

        if(tokens.length == 1) {
            // TODO : 접속 사용자 목록
        } else if(tokens.length == 2) {
            // TODO : 모니터 ON / OFF, 강퇴

        } else if (tokens.length == 3) {
            // TODO : 사용자 접속 차단 등록/삭제
        } else if (tokens.length == 4) {
            // TODO : log show
        }
    }
}
