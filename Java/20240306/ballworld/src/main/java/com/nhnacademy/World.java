package com.nhnacademy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel {
    private List<Ball> ballList;
    private List<Box> boxList;

    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public World() {
        this.ballList = new ArrayList<>();
        this.boxList = new ArrayList<>();
    }

    public List<Ball> getBallList() {
        return this.ballList;
    }

    public List<Box> getBoxList() {
        return this.boxList;
    }

    /**
     * 
     * @param ball
     * @throw IllegalArgumentException 공간을 벗어나거나, null인 경우, 볼 간 충돌된 경우
     */
    public void add(Ball ball) {
        if (ball == null) {
            throw new IllegalArgumentException();
        }

        if ((ball.getX() - ball.getRadius() < 0)
                || (ball.getX() + ball.getRadius() > getWidth())
                || (ball.getY() - ball.getRadius() < 0)
                || (ball.getY() + ball.getRadius() > getHeight())) {
            throw new IllegalArgumentException();
        }

        Rectangle newBallRegion = ball.getRegion();
        Rectangle existBallRegion;

        for (Ball existBall : ballList) {
            existBallRegion = existBall.getRegion();

            if (existBallRegion.intersects(newBallRegion)) {
                throw new IllegalArgumentException();
            }
        }

        getBallList().add(ball);
    }

    public void add(Box box) {
        if (box == null) {
            throw new IllegalArgumentException();
        }

        Rectangle newBoxRegion = box.getRegion();
        Rectangle existBoxRegion;

        for(Box existBox : getBoxList()) {
            existBoxRegion = existBox.getRegion();

            if(existBoxRegion.intersects(newBoxRegion)) {
                throw new IllegalArgumentException();
            }
        }

        getBoxList().add(box);
    }

    public void remove(Ball ball) {
        getBallList().remove(ball);
    }

    public void remove(Box box) {
        getBoxList().remove(box);
    }

    public void removeBall(int index) {
        getBallList().remove(index);
    }

    public void removeBox(int index) {
        getBoxList().remove(index);
    }

    public int getCountBall() {
        return getBallList().size();
    }

    public int getCountBox() {
        return getBoxList().size();
    }

    public Ball getBall(int index) {
        return getBallList().get(index);
    }

    public Box getBox(int index) {
        return getBoxList().get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Ball ball : getBallList()) {
            if (ball instanceof Paintable) {
                ((PaintableBall) ball).paint(g);
            }
        }

        for (Box box : getBoxList()) {
            if (box instanceof Paintable) {
                ((PaintableBox) box).paint(g);
            }
        }
    }
}