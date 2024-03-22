package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class User implements Runnable {

    int count;
    String address;
    int port;

    public User(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(address, port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
            System.out.println("서버에 연결되었습니다.");

            String consoleString;
            String message;

            while (!(message = reader.readLine()).equals("exit")) {
                if (message.length() != 0) {
                    System.out.println(message);
                }

                consoleString = consoleInput.readLine();
                writer.write(consoleString + "\n");
                writer.flush();
            }
        } catch (IOException | NullPointerException e) {
            System.err.println(e.getMessage());
        }
    };

}
