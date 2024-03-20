package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Exam02 {

    public static void main(String[] args) {
        int port = 1;

        for (int i = port; i <= 10000; i++) {
            try(Socket socket = new Socket("localhost", i);) {
                System.out.println("Port " + i + " 열려 있습니다.");

            } catch (UnknownHostException ignore) {
                //
            } catch (IOException ignore) {
                //
            }
        }
    }
}
