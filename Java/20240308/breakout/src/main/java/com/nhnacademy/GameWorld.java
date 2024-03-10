package com.nhnacademy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class GameWorld {
    static final int FRAME_WIDTH = 700;
    static final int FRAME_HEIGHT = 800;
    static final int MARGIN_WIDTH = 35;
    static final int MARGIN_HEIGHT = 20;
    static final int BALL_COUNT = 5;
    static final int BALL_X = 350;
    static final int BALL_Y = 600;
    static final int BOX_WIDTH = 70;
    static final int BOX_HEIGHT = 40;
    static final int MIN_DELTA = 5;
    static final int MAX_DELTA = 7;
    static final int MAX_MOVE_COUNT = 0;
    static final int DT = 10;
    static final int WALL_THICKNESS = 100;
    static final int MAX_BRICK_LIFE = 4;
    static final int CONTROLBAR_X = 430;
    static final int CONTROLBAR_Y = 690;
    static final int CONTROLBAR_WIDTH = 80;
    static final int CONTROLBAR_HEIGHT = 10;

    private static Random r = new Random();

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setTitle("BreakOut Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        MovableWorld world = new MovableWorld();
        setWorld(world);
        setBricks(world);
        setBalls(world);
        setControlBar(world);
        
        frame.add(world);
        frame.setVisible(true);

        world.run();
    }

    public static void setWorld(MovableWorld world) {
        world.setBounds(-WALL_THICKNESS, -WALL_THICKNESS,
                FRAME_WIDTH + WALL_THICKNESS * 2,
                FRAME_HEIGHT + WALL_THICKNESS * 2);
        world.add(new PaintableBox(-WALL_THICKNESS / 2, FRAME_HEIGHT / 2,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH / 2, -WALL_THICKNESS / 2,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH + WALL_THICKNESS / 2, FRAME_HEIGHT / 2,
                WALL_THICKNESS, FRAME_HEIGHT + 2 * WALL_THICKNESS));
        world.add(new PaintableBox(FRAME_WIDTH / 2, FRAME_HEIGHT + WALL_THICKNESS / 2,
                FRAME_WIDTH + 2 * WALL_THICKNESS, WALL_THICKNESS));
        world.setMaxMoveCount(MAX_MOVE_COUNT);
        world.setDT(DT);
    }

    public static void setBricks(MovableWorld world) {
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

                        world.add(new BreakableBox(BOX_WIDTH * j + MARGIN_WIDTH, BOX_HEIGHT * i + MARGIN_HEIGHT,
                                BOX_WIDTH, BOX_HEIGHT, lifeCount, boxColor));              
                }
        }
    }

    public static void setBalls(MovableWorld world) {
        int ballCount = 0;
        while(ballCount < BALL_COUNT) {
                BoundedBall ball = new BoundedBall(BALL_X, BALL_Y, 5, Color.BLUE);
                ball.setDX(r.nextInt(MAX_DELTA - MIN_DELTA) + MIN_DELTA);
                ball.setDY(r.nextInt(MAX_DELTA - MIN_DELTA) + MIN_DELTA);
                world.add(ball);
                ballCount++;
        }
    }

    public static void setControlBar(MovableWorld world) {
        world.addControlBar(new MovableBox(CONTROLBAR_X, CONTROLBAR_Y, CONTROLBAR_WIDTH, CONTROLBAR_HEIGHT));
    }
}
