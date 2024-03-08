package com.nhnacademy;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestWorld {

    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;
    static final int MIN_RADIUS = 10;
    static final int MAX_RADIUS = 50;
    static final int BALL_COUNT = 5;
    static final int MAX_DELTA = 30;
    static final int MIN_DELTA = 10;
    static final int DEFAULT_DT = 70;
    static final int MAX_MOVE_COUNT = 0;
    static final int BOX_COUNT = 10;
    static final int MIN_SIZE = 50;
    static final int MAX_SIZE = 100;
    static final Color[] COLORS = new Color[] {
        Color.BLUE,
        Color.RED,
        Color.WHITE,
        Color.BLACK,
        Color.GREEN
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Random r = new Random();
        MovableWorld world = new MovableWorld();
        int x;
        int y;
        int dx;
        int dy;
        int radius;
        int width;
        int height;
        Color color;

        world.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        world.setMaxMoveCount(MAX_MOVE_COUNT);
        world.setDT(DEFAULT_DT);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        while(world.getCountBall() < BALL_COUNT) {
            try {
                x = r.nextInt(FRAME_WIDTH);
                y = r.nextInt(FRAME_HEIGHT);
                dx = MIN_DELTA + r.nextInt(MAX_DELTA - MIN_DELTA + 1);
                dy = MIN_DELTA + r.nextInt(MAX_DELTA - MIN_DELTA + 1);
                radius = MIN_RADIUS + r.nextInt(MAX_RADIUS - MIN_RADIUS + 1);
                color = COLORS[r.nextInt(COLORS.length)];

                BoundedBall ball = new BoundedBall(x, y, radius, color);
                ball.setDX(dx);
                ball.setDY(dy);
                ball.setBounds(world.getBounds());
                world.add(ball);
            } catch(IllegalArgumentException ignore) {
                //
            }
        }

        while(world.getCountBox() < BOX_COUNT) {
            try {
                x = r.nextInt(FRAME_WIDTH);
                y = r.nextInt(FRAME_HEIGHT);
                width = MIN_SIZE + r.nextInt(MAX_SIZE - MIN_SIZE + 1);
                height = r.nextInt(MAX_SIZE - MIN_SIZE + 1);
                color = COLORS[r.nextInt(COLORS.length)];

                PaintableBox box = new PaintableBox(x, y, width, height, color);
                world.add(box);
            } catch(IllegalArgumentException ignore) {
                //
            }
        }

        frame.add(world);
        frame.setVisible(true);
        
        world.run();
    }
    
}
