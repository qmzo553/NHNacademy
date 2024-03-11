package com.nhnacademy;

import java.awt.Color;

public class BreakableBox extends PaintableBox implements Breakable {
    
    private int lifeCount = 0;

    public BreakableBox(int x, int y, int width, int height, int lifeCount, Color color) {
        super(x, y, width, height, color);

        this.lifeCount = lifeCount;
    }

    public int getLifeCount() {
        return this.lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public void breakDown() {
        if(getLifeCount() >= 4) {
            setLifeCount(++lifeCount);
        }
        setLifeCount(--lifeCount);
    }
}
