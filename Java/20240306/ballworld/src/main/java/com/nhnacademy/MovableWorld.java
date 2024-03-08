package com.nhnacademy;

import java.lang.Thread;

public class MovableWorld extends World {

    private int moveCount;
    private int maxMoveCount = 0;
    private int dt = 0;

    public int getMoveCount() {
        return this.moveCount;
    }

    public int getMaxMoveCount() {
        return this.maxMoveCount;
    }

    public int getDT() {
        return this.dt;
    }

    public void setMaxMoveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        this.maxMoveCount = count;
    }

    public void setDT(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }
        this.dt = dt;
    }

    public void reset() {
        this.moveCount = 0;
    }

    public void move() {
        if ((getMaxMoveCount() == 0) || getMoveCount() < getMaxMoveCount()) {
            // List<Ball> removelist = new LinkedList<>();
            for (int i = 0; i < getCountBall(); i++) {
                Ball ball1 = getBall(i);
                if (ball1 instanceof MovableBall) {
                    ((MovableBall) ball1).move();

                    if (ball1 instanceof BoundedBall) {
                        for (int j = 0; j < getCountBall(); j++) {
                            Ball ball2 = getBall(j);

                            if (!(ball1.equals(ball2)) && ball1.getRegion().intersects(ball2.getRegion())) {
                                logger.info("ball {} 과 ball {}이 충돌했습니다.", ball1.getId(), ball2.getId());
                                ((BoundedBall) ball1).bounce(ball2);

                                // removeList.add(ball2);
                            }
                        }
                    }
                }
            }
        }

        this.moveCount++;
        repaint();

        // for(BAll ball : removeList) {remove(ball)};
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(getDT());
                move();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
