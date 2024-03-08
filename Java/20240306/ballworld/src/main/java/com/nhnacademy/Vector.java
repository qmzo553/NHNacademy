package com.nhnacademy;

public class Vector {
    private int dx = 0;
    private int dy = 0;

    public Vector() {
    }

    public Vector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDX() {
        return this.dx;
    }

    public int getDY() {
        return this.dy;
    }

    public void setDX(int dx) {
        this.dx = dx;
    }

    public void setDY(int dy) {
        this.dy = dy;
    }

    public void set(int dx, int dy) {
        setDX(dx);
        setDY(dy);
    }

    public void set(Vector other) {
        setDX(other.getDX());
        setDY(other.getDY());
    }
    
    public void turnDX() {
        setDX(-getDX()); 
    }

    public void turnDY() {
        setDY(-getDY());
    }

    public void add(Vector other) {
        setDX(getDX() + other.getDX());
        setDY(getDY() + other.getDY());

        // set(getDX() + other.getDX(), getDY() + other.getDY());
    }

    public void sub(Vector other) {
        setDX(getDX() - other.getDX());  
        setDY(getDY() - other.getDY());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Vector) 
        && (((Vector) other).getDX() == getDX())   
        && (((Vector) other).getDY() == getDY());
    }
}
