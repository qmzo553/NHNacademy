package com.nhnacademy;

import java.net.Socket;

public class Exam03 {
    public static void main(String[] args) {
        // int port = Integer.parseInt(args[0]);
        // String address = args[1];

        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("서버에 연결 되었습니다.");
            System.out.println("Local address : " + socket.getLocalAddress());
            System.out.println("Local port : " + socket.getLocalPort());
            System.out.println("Remote address : " + socket.getInetAddress());
            System.out.println("Remote port : " + socket.getPort());
        } catch (Exception e) {
            //
        }
    }
}
