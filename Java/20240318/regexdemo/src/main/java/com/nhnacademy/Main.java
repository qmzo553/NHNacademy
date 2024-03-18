package com.nhnacademy;

public class Main {
    public static void main(String[] args) {
        for(String s : args) {
            try {
                int value = Integer.parseInt(s);
                System.out.println("int : " + value);
            } catch(NumberFormatException ignore) {
                System.out.println("String : " + s);
            }
        }
    }
}