package com.nhnacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Exam06 {
    
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
            System.out.println("서버에 연결되었습니다.");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;
            while(!(inputLine = bufferedReader.readLine()).equals("exit")) {
                socket.getOutputStream().write(inputLine.getBytes());
                socket.getOutputStream().write("\n".getBytes());
            }
        } catch(Exception ignore) {
            //
        }
    }
}
