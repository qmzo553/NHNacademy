package com.nhnacademy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;

public class World extends JPanel implements MouseListener{
    List<Regionable> regionableList = new LinkedList<>();

    public World() {
        addMouseListener(this);
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

        for (Regionable item : regionableList) {    
            if(((object instanceof Bounded) || (item instanceof Bounded)) && object.getRegion().intersects(item.getRegion())) {
                throw new IllegalArgumentException();
            }
            
        }

        regionableList.add(object);
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Regionable object : regionableList) {
            if (object instanceof Paintable) {
                ((Paintable) object).paint(g);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        add(new PaintableBox(e.getX(), e.getY(), 50, 50));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
