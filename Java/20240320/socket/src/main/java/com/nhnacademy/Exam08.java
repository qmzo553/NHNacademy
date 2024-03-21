package com.nhnacademy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam08 {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();

                System.out.println("Connected : " + socket.getInetAddress().getHostAddress());
                socket.getOutputStream().write("hello\n".getBytes());
                socket.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}