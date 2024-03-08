package com.nhnacademy;

import java.awt.Color;

public class MovableBall extends PaintableBall implements Movable {

    Vector motion = new Vector();

    public MovableBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public int getDX() {
        return this.motion.getDX();
    }

    public int getDY() {
        return this.motion.getDY();
    }

    public Vector getVector() {
        return this.motion;
    }

    public void setDX(int dx) {
        this.motion.setDX(dx);
    }

    public void setDY(int dy) {
        this.motion.setDY(dy);
    }

    public void move() {
        // setX(getX() + getDX());
        // setY(getY() + getDY());
        moveTo(getX() + getDX(), getY() + getDY());
        logger.trace("{} : {}, {}, {}, {}", getId(), getX(), getY(), getRegion().getX(), getRegion().getY());
    }

    public void moveTo(int x, int y) {
        setX(x);
        setY(y);
    }
}
