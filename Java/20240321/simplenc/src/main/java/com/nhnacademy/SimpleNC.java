package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleNC {

    static int port;

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("올바른 옵션을 입력해 주세요.");
        }

        if (!args[0].equals("snc")) {
            throw new IllegalArgumentException("올바른 옵션을 입력해 주세요.");
        }

        if (!(args[1].equals("-l")) && IPAdressVaildator.isIPAddress(args[1]) && !(args[1].equals("localhost"))) {
            throw new IllegalArgumentException("올바른 옵션을 입력해 주세요.");
        }

        try {
            port = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.err.println("포트 번호는 숫자여야 합니다.");
        }

        if (args[1].equals("-l")) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = serverSocket.accept();
                    
                    System.out.println("Connected : " + socket.getInetAddress().getHostAddress());

                    Thread thread = new Thread(() -> {
                        try (
                                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(socket.getInputStream()));
                                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                                BufferedWriter writer = new BufferedWriter(
                                        new OutputStreamWriter(socket.getOutputStream()));) {

                            String consoleString;
                            String message;

                            while (!(message = reader.readLine()).equals("exit")) {
                                System.out.println(message);

                                consoleString = consoleInput.readLine();
                                writer.write(consoleString + "\n");
                                writer.flush();
                            }
                        } catch (IOException | NullPointerException e) {
                            System.err.println(e.getMessage());
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                System.err.println("Error closing socket: " + e.getMessage());
                            }
                        }
                    });
                    thread.start();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            Thread thread = new Thread(() -> {
                try (Socket socket = new Socket(args[1], port);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                    System.out.println("서버에 연결되었습니다.");

                    String consoleString;
                    String message;

                    while (!(message = reader.readLine()).equals("exit")) {
                        if(message.length() != 0) {
                            System.out.println(message);
                        }

                        consoleString = consoleInput.readLine();
                        writer.write(consoleString + "\n");
                        writer.flush();
                    }
                } catch (IOException | NullPointerException e) {
                    System.err.println(e.getMessage());
                }
            });
            thread.start();
        }
    }
}