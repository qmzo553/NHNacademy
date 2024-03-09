package com.nhnacademy;

import java.awt.Color;
import java.awt.Rectangle;

public class BreakableBox extends PaintableBox implements Breakable {

    public BreakableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
}
