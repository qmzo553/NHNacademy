package com.nhnacademy;

import java.awt.Color;

public class MovableBox extends PaintableBox implements Movable {
    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;

    final Vector motion = new Vector();

    public MovableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public Vector getMotion() {
        return motion;
    }

    public int getDX() {
        return motion.getDX();
    }

    public int getDY() {
        return motion.getDY();
    }

    public void setDX(int dx) {
        motion.setDX(dx);
    }

    public void setDY(int dy) {
        motion.setDY(dy);
    }

    public void move() {
        moveTo(getX() + getDX(), getY() + getDY());
    }

    public void moveTo(int x, int y) {
        setX(x);
        setY(y);
    }
}
