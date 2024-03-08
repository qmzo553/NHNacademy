package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;

public class PaintableBox extends Box implements Paintable {
    static final Color DEFAUILT_COLOR = Color.BLACK;
    private Color color = DEFAUILT_COLOR;

    public PaintableBox(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAUILT_COLOR);
    }

    public PaintableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);

        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public void paint(Graphics g) {
        if (g == null) {
            throw new IllegalArgumentException();
        }

        Color originalColor = g.getColor();

        g.setColor(getColor());
        g.fillRect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());

        g.setColor(originalColor);
    }
}
