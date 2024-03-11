package com.nhnacademy;

import java.util.Random;

public class MovableWorld extends World {
    static final int CONTROLBAR_MAX_WIDTH = 80;
    static final int CONTROLBAR_MIN_WIDTH = 50;

    private Random r = new Random();
    static final int DEFAULT_DT = 10;
    int moveCount;
    int maxMoveCount = 0;
    int dt = DEFAULT_DT;

    public void setDT(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }
        this.dt = dt;
    }

    public int getDT() {
        return this.dt;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public int getMaxMoveCount() {
        return this.maxMoveCount;
    }

    public void setMaxMoveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        this.maxMoveCount = count;
    }

    public void reset() {
        this.moveCount = 0;
    }

    public void move() {
        if ((getMaxMoveCount() == 0) || (getMoveCount() < getMaxMoveCount())) {
            for (int i = 0; i < getCount(); i++) {
                Regionable object = get(i);
                if (object instanceof Movable) {
                    ((Movable) object).move();
                    checkCollisions(object);
                    checkControlBarCollision(object);
                    checkDrop(object);
                }
            }
    
            this.moveCount++;
            repaint();
        }
    }
    
    private void checkCollisions(Regionable object) {
        if (object instanceof Bounded) {
            for (int j = 0; j < getCount(); j++) {
                Regionable other = get(j);
                if ((object != other) && (object.getRegion().intersects(other.getRegion()))) {
                    ((Bounded) object).bounce(other);
                    checkBreakableCollision(other);
                }
            }
        }
    }
    
    private void checkBreakableCollision(Regionable other) {
        if (other instanceof Breakable) {
            ((Breakable) other).breakDown();
            if (((Breakable) other).getLifeCount() == 0) {
                remove(other);
            }
        }
    }
    
    private void checkControlBarCollision(Regionable object) {
        if (object.getRegion().intersects(getControlBar().getRegion())) {
            ((Bounded) object).bounce(getControlBar());

            addControlBar(new MovableBox(getControlBar().getX(), getControlBar().getY(),
                r.nextInt(CONTROLBAR_MAX_WIDTH - CONTROLBAR_MIN_WIDTH) + CONTROLBAR_MIN_WIDTH, getControlBar().getHeight()));
        }
    }

    private void checkDrop(Regionable object) {
        if(object.getY() > getHeight()) {
            remove(object);
        }
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            move();
            try {
                Thread.sleep(getDT());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
