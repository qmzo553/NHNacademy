package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Exam01 {
    
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("서버에 연결되었습니다.");

        } catch (UnknownHostException e) {
            System.err.println("Host를 찾을 수 없습니다. : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Socket에 오류가 발생하였습니다. : " + e.getMessage());
        }
    }
}
