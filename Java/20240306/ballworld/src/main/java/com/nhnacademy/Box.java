package com.nhnacademy;

import java.awt.Rectangle;

public class Box {

    static int count = 0;
    private int id = ++count;
    private Rectangle region;

    public Box(int x, int y, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }

        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE
                || y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }

        this.region = new Rectangle(x - width / 2, y - width / 2, width, height);
    }

    public int getX() {
        return (int) getRegion().getCenterX();
    }

    public int getY() {
        return (int) getRegion().getCenterY();
    }

    public int getWidth() {
        return (int) getRegion().getWidth();
    }

    public int getHeight() {
        return (int) getRegion().getHeight();
    }

    public int getId() {
        return this.id;
    }

    public Rectangle getRegion() {
        return this.region;
    }
}
