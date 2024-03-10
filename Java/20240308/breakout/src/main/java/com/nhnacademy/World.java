package com.nhnacademy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;

public class World extends JPanel implements MouseMotionListener{
    List<Regionable> regionableList = new LinkedList<>();
    private MovableBox controlBar;

    public World() {
        addMouseMotionListener(this);
    }
    /**
     *
     * @param object
     * @throw IllegalArgumentException 공간을 벗어나거나, null인 경우, 볼간 충돌된 경우
     */
    public void add(Regionable object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }

        regionableList.add(object);
    }

    public void addControlBar(MovableBox object) {
        if(object == null) {
            throw new IllegalArgumentException();
        }
        
        this.controlBar = object;
    }

    public void remove(Regionable object) {
        regionableList.remove(object);
    }

    @Override
    public void remove(int index) {
        regionableList.remove(index);
    }

    public int getCount() {
        return regionableList.size();
    }

    public Regionable get(int index) {
        return regionableList.get(index);
    }

    public PaintableBox getControlBar() {
        return this.controlBar;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Regionable object : regionableList) {
            if (object instanceof Paintable) {
                ((Paintable) object).paint(g);
            }
        }

        controlBar.paint(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        moveControlBar(e.getX());
    }

    private void moveControlBar(int x) {
        int barX = x - controlBar.getWidth() / 2;

        if (barX < 0) {
            barX = 0;
        } else if (barX > getWidth() - getControlBar().getWidth()) {
            barX = getWidth() - getControlBar().getWidth();
        }

        controlBar.moveTo(barX, getControlBar().getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
