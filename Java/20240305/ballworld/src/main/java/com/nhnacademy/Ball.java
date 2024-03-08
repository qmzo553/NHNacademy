package com.nhnacademy;

public class Ball {

    private int x;
    private int y;
    private int radius;

    public Ball(int x, int y, int radius) {
        if(x + (long)radius > Integer.MAX_VALUE || x - (long)radius < Integer.MIN_VALUE
          || y + (long)radius > Integer.MAX_VALUE || y - (long)radius < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("볼의 좌표는 최댓값, 최솟값 안에 있어야 합니다.");
        }

        if(radius <= 0) {
            throw new IllegalArgumentException("반지름은 0보다 커야 합니다.");
        }

        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRadius() {
        return this.radius;
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y + ", " + this.radius;
        // return String.format("(%d,%d,%d)", getX(), getY(), getRadius());
    }
    
}
