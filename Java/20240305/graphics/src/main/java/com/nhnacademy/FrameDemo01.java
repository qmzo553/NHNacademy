package com.nhnacademy;

import javax.swing.JFrame;

import java.awt.Graphics;
import javax.swing.JPanel;

public class FrameDemo01 {

    public static class MyCanvas extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            drawTriAngle(g, 500, 500, 250, 10);
        }

        private void drawTriAngle(Graphics g, int x, int y, int rate, int depth) {
            if (depth == 0) {
                // 재귀 호출의 종료 조건
                return;
            } else {
                // 삼각형 그리기
                g.drawLine(x, y - rate, x + rate, y + rate);
                g.drawLine(x, y - rate, x - rate, y + rate);
                g.drawLine(x - rate, y + rate, x + rate, y + rate);
                // 재귀 호출하여 다음 단계의 삼각형 그리기
                drawTriAngle(g, x, y - rate / 2,  rate / 2, depth - 1);
                drawTriAngle(g, x - rate / 2, y + rate / 2, rate / 2, depth - 1);
                drawTriAngle(g, x + rate / 2, y + rate / 2, rate / 2, depth - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("FrameDemo01");
    
        frame.setSize(1000, 1000);

        MyCanvas panel = new MyCanvas();
        frame.add(panel);

        frame.setVisible(true);
    }
}