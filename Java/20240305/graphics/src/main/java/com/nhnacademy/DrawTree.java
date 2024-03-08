package com.nhnacademy;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTree extends JPanel {
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, 500, 800, -Math.PI / 2, 800 / 4, 15);
    }
    
    private void drawTree(Graphics g, int x, int y, double angle, double length, int depth) {
        if (depth == 0) {
            // 재귀 호출의 종료 조건
            return;
        } else {
            // 다음 좌표 계산
            int x2 = x + (int) (length * Math.cos(angle));
            int y2 = y + (int) (length * Math.sin(angle));
            
            g.setColor(Color.BLUE);
            // 선 그리기
            g.drawLine(x, y, x2, y2);
            
            // 재귀 호출하여 다음 단계의 나무 그리기
            drawTree(g, x2, y2, angle - Math.toRadians(25), length * 0.7, depth - 1);
            drawTree(g, x2, y2, angle + Math.toRadians(25), length * 0.7, depth - 1);
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        frame.add(new DrawTree());
        frame.setVisible(true);
    }
}