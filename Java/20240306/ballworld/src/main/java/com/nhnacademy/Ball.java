package com.nhnacademy;

import java.awt.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ball {

    static int count = 0;
    private int id = ++count;
    private Rectangle region;

    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public Ball(int x, int y, int radius) {
        if(x + (long)radius > Integer.MAX_VALUE || x - (long)radius < Integer.MIN_VALUE
          || y + (long)radius > Integer.MAX_VALUE || y - (long)radius < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("볼의 좌표는 최댓값, 최솟값 안에 있어야 합니다.");
        }

        if(radius <= 0) {
            throw new IllegalArgumentException("반지름은 0보다 커야 합니다.");
        }

        this.region = new Rectangle(x - radius, y - radius, 2 * radius, 2 * radius);

        logger.trace("ball 추가 : [{}, {}, {}]", x, y, radius);
    }

    public int getX() {
        return (int) getRegion().getCenterX();
    }

    public int getY() {
        return (int) getRegion().getCenterY();
    }

    public int getId() {
        return id;
    }

    public void setX(int x) {
        getRegion().setLocation(x - getRadius(), getY() - getRadius());
    }

    public void setY(int y) {
        getRegion().setLocation(getX() - getRadius(), y - getRadius());
    }

    public int getRadius() {
        return getX() - (int) getRegion().getMinX();
    }

    public Rectangle getRegion() {
        return this.region;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY() + ", " + getRadius();
        // return String.format("(%d,%d,%d)", getX(), getY(), getRadius());
    }
    
}
