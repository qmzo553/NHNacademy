package com.nhnacademy;

public class Main {
    public static void main(String[] args) {
        System.out.println(Math.cos(Math.toRadians(60)));
        System.out.println(Math.sin(Math.toRadians(60)));

        System.out.println(getLength(10, 10, 50, 50));
        print(200, 200, 100, 100, 20);
    }

    public static void print(int x1, int y1, int x2, int y2, int num) {
        System.out.println(x1 + ", " + y1 + ", " + x2 + ", " + y2);
        if(getLength(x1, y1, x2, y2) > 5) {

            print(x2, y2, x2 - (int) (num * Math.cos(Math.toRadians(60))), y2 - (int) (num * Math.sin(Math.toRadians(60))), num + 5);
            print(x2, y2, x2 - (int) (num * Math.cos(Math.toRadians(60))), y2 - (int) (num * Math.sin(Math.toRadians(60))), num + 5);
        } 
    }

    public static int getLength(int x1, int y1, int x2, int y2) {
        int xd = (int) Math.pow(x1 - x2, 2);
        int yd = (int) Math.pow(y1 - y2, 2);
        System.out.println(Math.sqrt(xd + yd));
        return (int) Math.sqrt(xd + yd);
    }
}