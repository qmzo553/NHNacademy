package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;

public class PaintableBall extends Ball {
    static final Color DEFAUILT_COLOR = Color.BLACK;
    private Color color = DEFAUILT_COLOR;
    
    public PaintableBall(int x, int y, int radius) {
        this(x, y, radius, DEFAUILT_COLOR);
    }    

    public PaintableBall(int x, int y, int radius, Color color) {
        super(x, y, radius);

        if(color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    /**
     * 
     * @param color
     * @throws IllegalArgumentException color는 null 허용하지 않습니다.
     */
    public void setColor(Color color) {
        if(color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public void paint(Graphics g) {
        if(g == null) {
            throw new IllegalArgumentException();
        }

        Color originalColor = g.getColor();

        g.setColor(getColor());
        g.fillOval(getX(), getY(), getRadius() * 2, getRadius() * 2);

        g.setColor(originalColor);
    }
}
