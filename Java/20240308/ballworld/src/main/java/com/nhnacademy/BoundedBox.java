package com.nhnacademy;

import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBox extends MovableBox implements Bounded {

    public BoundedBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void move() {
        super.move();
    }

    public void bounce(Regionable other) {
        Rectangle intersection = getRegion().intersection(other.getRegion());

        if (getRegion().getHeight() != intersection.getHeight()) {
            setDY(-getDY());
        }

        if (getRegion().getWidth() != intersection.getWidth()) {
            setDX(-getDX());
        }

    }
}
