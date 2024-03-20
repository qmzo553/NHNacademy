package com.nhnacademy;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Exam04 {
    
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;
        
        if(args.length > 0) {
            host = args[0];
        }

        try {
            if(args.length > 1) {
                port = Integer.parseInt(args[1]);
            }
        } catch (NumberFormatException ignore) {
            System.out.println("Port가 올바르지 않습니다.");
            System.exit(1);
        }

        try(Socket socket = new Socket(host, port)) {
            System.out.println("서버에 연결하였습니다.");
            OutputStream out = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);

            while(true) {
                String message = sc.nextLine(); 

                if(message.equals("exit")) {
                    break;
                }

                out.write((message + "\n").getBytes());
            }

            System.out.println("서버와의 연결이 끝났습니다.");
        } catch(Exception ignore) {
            System.err.println(host + ":" + port + "에 연결할 수 없습니다.");
        }
    }
}
