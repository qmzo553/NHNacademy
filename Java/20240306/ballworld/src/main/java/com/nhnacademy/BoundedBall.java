package com.nhnacademy;

import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBall extends MovableBall {

    private Rectangle bounds;

    public BoundedBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    @Override
    public void move() {
        super.move();

        if (isOutOfBounds()) {
            bounce();
        }
    }

    public boolean isOutOfBounds() {
        Rectangle region = new Rectangle(getX() - getRadius(), getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
        Rectangle intersection = bounds.intersection(region);

        return intersection.getWidth() != region.getWidth() || intersection.getHeight() != region.getHeight();
    }

    public void bounce() {
        if (getX() - getRadius() < getBounds().getMinX() || (getX() + getRadius() > getBounds().getMaxX())) {
            getVector().turnDX();
        }

        if (getY() - getRadius() < getBounds().getMinY() || getY() + getRadius() > getBounds().getMaxY()) {
            getVector().turnDY();
        }
    }

    public void bounce(Ball other) {
        Rectangle intersection = getRegion().intersection(other.getRegion());

        if (intersection.getHeight() != other.getRegion().getHeight()) {
            getVector().turnDY();
        }
        
        if (intersection.getWidth() != other.getRegion().getWidth()) {
            getVector().turnDX();
        }
    }
}
