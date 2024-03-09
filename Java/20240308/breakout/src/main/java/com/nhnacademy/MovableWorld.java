package com.nhnacademy;

public class MovableWorld extends World {
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
        return dt;
    }

    public void reset() {
        moveCount = 0;
    }

    public void move() {
        if ((getMaxMoveCount() == 0) || (getMoveCount() < getMaxMoveCount())) {
            for (int i = 0; i < getCount(); i++) {
                Regionable object = get(i);
                if (object instanceof Movable) {
                    ((Movable) object).move();
                    checkCollisions(object);
                    checkControlBarCollision(object);
                }
            }
    
            moveCount++;
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

    public int getMoveCount() {
        return moveCount;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        maxMoveCount = count;
    }

}
