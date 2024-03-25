package com.nhnacademy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ChatRoomServer implements Runnable {
    private static final String CLIENT_ID = "client_id";
    private static final String TARGET_ID = "target_id";
    private static final String MESSAGELOG_PATH = "./message.log";
    private static final String SERVERSETTINGS_PATH = "./serverSettings.json";

    int port;
    ServerSocket serverSocket;
    List<Thread> clientHandlerList = new LinkedList<>();
    List<ChatRoomHandler> chatRoomHandlerList = new LinkedList<>();
    List<ChatRoomHandler> allowedChatRoomHandlerList = new LinkedList<>();
    List<String> userList = new LinkedList<>();
    Queue<JSONObject> responseList = new LinkedList<>();
    List<String> denyList = new LinkedList<>();
    boolean monitorOpt = true;

    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public ChatRoomServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Thread consoleAgent = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try (BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {
                    String line;
                    while ((line = consoleInput.readLine()) != null) {
                        commandHandler(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread inputAgent = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                while (!responseList.isEmpty()) {
                    JSONObject message = responseList.poll();
                    logger.trace("JSON message: {}", message);
                    String targetClientId;

                    if (message.has(TARGET_ID)) {
                        targetClientId = message.getString(TARGET_ID);
                        message.remove(TARGET_ID);
                    } else {
                        targetClientId = message.getString(CLIENT_ID);
                    }

                    synchronized (chatRoomHandlerList) {
                        for (ChatRoomHandler chatRoomHandler : chatRoomHandlerList) {
                            String handlerClientId = chatRoomHandler.getClientId();
                            if (handlerClientId.equals(targetClientId)) {
                                chatRoomHandler.send(message + "\n");
                            }
                        }
                    }
                }
            }
        });

        Thread outputAgent = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (chatRoomHandlerList) {
                    for (ChatRoomHandler chatRoomHandler : chatRoomHandlerList) {
                        if (!chatRoomHandler.isEmptyReceiveQueue()) {
                            String line = chatRoomHandler.receive();

                            JSONObject request = new JSONObject(line);
                            logger.trace("JSON message: {}", request);

                            if (monitorOpt) {
                                System.out.println(request.toString(3));
                            }

                            String type = request.getString("type");
                            
                            if (type.equals("connect")) {
                                connectionHandler(request, chatRoomHandler);
                            } else if (type.equals("message")) {
                                String sendClientId = chatRoomHandler.getClientId();
                                if(userList.contains(sendClientId)) {
                                    messageHandler(request, sendClientId);
                                } 
                            } else if(type.equals("client_list")) {
                                String sendClientId = chatRoomHandler.getClientId();
                                clientListHandler(request, sendClientId);
                            }
                        }
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignore) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        consoleAgent.start();
        inputAgent.start();
        outputAgent.start();

        try {
            roadSettings();
            serverSocket = new ServerSocket(port);
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                ChatRoomHandler chatRoomHandler = new ChatRoomHandler(socket);
                Thread thread = new Thread(chatRoomHandler);
                thread.start();
                clientHandlerList.add(thread);
                synchronized (chatRoomHandlerList) {
                    chatRoomHandlerList.add(chatRoomHandler);
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectionHandler(JSONObject message, ChatRoomHandler chatRoomHandler) {
        String clientId = message.getString(CLIENT_ID);

        if (denyList.contains(clientId)) {
            // 접속 불가
            message.put("response", "deny");
            responseList.add(message);
        } else {
            // 접속 허용
            userList.add(clientId);
            message.put("response", "ok");
            System.out.println(message.toString(3));
            responseList.add(message);
            chatRoomHandler.setClientId(clientId);
            saveSettings();
        }
    }

    private void messageHandler(JSONObject request, String sendClientId) {
        request.put(CLIENT_ID, sendClientId);
        responseList.add(request);
    }

    private void clientListHandler(JSONObject request, String sendClientId) {
        request.put(TARGET_ID, sendClientId);
        for(String str : userList) {
            System.out.println(str);
        }
        request.put("client_list", userList);

        System.out.println(request.toString(3));
    }

    private void commandHandler(String command) {
        String[] tokens = command.split(" ");

        if (tokens[0].equals("log")) {
            if (tokens.length == 2) {
                handleLogShowCommand(0, 10);
            } else if (tokens.length == 3) {
                int end = Integer.parseInt(tokens[2]);
                handleLogShowCommand(0, end);
            } else if (tokens.length == 4) {
                int start = Integer.parseInt(tokens[2]);
                int end = Integer.parseInt(tokens[3]);
                handleLogShowCommand(start, end);
            }
        }

        if (tokens.length == 1 && tokens[0].equals("client_list")) {
            handleClientListCommand();
        } else if (tokens.length == 2) {
            if (tokens[0].equals("monitor")) {
                handleMonitorCommand(tokens[1]);
            } else if (tokens[0].equals("send_off")) {
                String clientId = tokens[1];
                handleSendOffCommand(clientId);
            }
        } else if (tokens.length == 3) {
            String clientId = tokens[2];
            handleDenyListCommand(tokens[1], clientId);
        } else {
            System.out.println("존재하지 않은 명령어 입니다. 다시 입력해 주세요.");
        }

    }

    private void handleClientListCommand() {
        if (!userList.isEmpty()) {
            System.out.println("현재 접속 중인 사용자 목록");
            for (String clientId : userList) {
                System.out.println("CLIENT ID : " + clientId);
            }
        } else {
            System.out.println("현재 접속 중인 사용자가 없습니다.");
        }
    }

    private void handleMonitorCommand(String option) {
        if (option.equals("ON")) {
            monitorOpt = true;
            System.out.println("모니터 ON");
        } else if (option.equals("OFF")) {
            monitorOpt = false;
            System.out.println("모니터 OFF");
        }
    }

    private void handleSendOffCommand(String clientId) {
        if(userList.contains(clientId)) {
            userList.remove(clientId);
            System.out.println("client " + clientId + " 이 강퇴 되었습니다.");
        } else {
            System.out.println("client " + clientId + " 는 존재하지 않습니다.");
        }     
    }

    private void handleDenyListCommand(String action, String clientId) {
        if (action.equals("add")) {
            denyList.add(clientId);
            System.out.println("클라이언트 " + clientId + " 이 차단 되었습니다.");
        } else if (action.equals("del")) {
            denyList.remove(clientId);
            System.out.println("클라이언트 " + clientId + " 이 차단 해제 되었습니다.");
        }
        saveSettings();
    }

    private void handleLogShowCommand(int start, int end) {
        if (start < 0 || end < start) {
            logger.error("유효하지 않은 범위 입니다.");
            return;
        }

        String logFilePath = MESSAGELOG_PATH;
        // 로그 출력
        try (LineNumberReader reader = new LineNumberReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int lineNumber = reader.getLineNumber();
                if (lineNumber >= start && lineNumber <= end) {
                    System.out.println(line);
                }
                if (lineNumber > end) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSettings() {
        JSONObject serverSettings = new JSONObject();
        serverSettings.put("deny", denyList);

        try (FileWriter fileWriter = new FileWriter(SERVERSETTINGS_PATH)) {
            fileWriter.write(serverSettings.toString());
            System.out.println("complete save");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void roadSettings() {
        try (FileReader fileReader = new FileReader(SERVERSETTINGS_PATH)) {
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject serverSettings = new JSONObject(jsonTokener);

            denyList = getDenyList(serverSettings);

            System.out.println("complete load");
        } catch (IOException e) {
            System.out.println("server setting 파일이 존재 하지 않습니다.");
        }
    }

    private static List<String> getDenyList(JSONObject serverSettings) {
        List<String> denyList = new LinkedList<>();

        if (serverSettings.has("deny")) {
            JSONArray denyArray = serverSettings.getJSONArray("deny");
            for (int i = 0; i < denyArray.length(); i++) {
                String deny = denyArray.getString(i);
                denyList.add(deny);
            }
        }
        return denyList;
    }
}
