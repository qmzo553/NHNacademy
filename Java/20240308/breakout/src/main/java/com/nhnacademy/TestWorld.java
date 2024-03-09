package com.nhnacademy;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestWorld {
    static final int FRAME_WIDTH = 700;
    static final int FRAME_HEIGHT = 1000;
    static final int BOX_WIDTH = 70;
    static final int BOX_HEIGHT = 50;
    static final int MIN_DELTA = 5;
    static final int MAX_DELTA = 7;
    static final int MAX_MOVE_COUNT = 0;
    static final int DT = 10;
    static final int WALL_THICKNESS = 100;
    static final int MAX_BRICK_LIFE = 4;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Random r =  new Random();

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MovableWorld world = new MovableWorld();
        world.setBounds(-WALL_THICKNESS, -WALL_THICKNESS,
                FRAME_WIDTH + WALL_THICKNESS * 2,
                FRAME_HEIGHT + WALL_THICKNESS * 2);
        frame.add(world);
        world.add(new PaintableBox(-WALL_THICKNESS / 2, FRAME_HEIGHT / 2,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH / 2, -WALL_THICKNESS / 2,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH + WALL_THICKNESS / 2, FRAME_HEIGHT / 2,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH / 2, FRAME_HEIGHT + WALL_THICKNESS / 2,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS));

        for(int i = 0; i < FRAME_HEIGHT / BOX_HEIGHT / 2; i++) {
                for(int j = 0; j < FRAME_WIDTH / BOX_WIDTH; j++) {
                        int lifeCount = r.nextInt(MAX_BRICK_LIFE) + 1;
                        Color boxColor = null;
                        switch (lifeCount) {
                                case 1:
                                        boxColor = Color.BLUE;
                                        break;
                                case 2:
                                        boxColor = Color.GREEN;
                                        break;
                                case 3:
                                        boxColor = Color.RED;
                                        break;
                                case 4:
                                        boxColor = Color.BLACK;
                                        break;
                                default:
                                        break;
                        }

                        world.add(new BreakableBox(BOX_WIDTH * j, BOX_HEIGHT * i,
                                BOX_WIDTH, BOX_HEIGHT, lifeCount, boxColor));              
                }
        }
        
        BoundedBall ball = new BoundedBall(600, 600, 5, Color.BLUE);
        ball.setDX(5);
        ball.setDY(7);
        world.add(ball);
        world.addControlBar(new PaintableBox(350 - 30, 700 - 10, 80, 10));
        frame.setVisible(true);

        world.setMaxMoveCount(MAX_MOVE_COUNT);
        world.setDT(DT);
        world.run();
    }
}
