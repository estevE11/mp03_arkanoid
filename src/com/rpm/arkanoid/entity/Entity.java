package com.rpm.arkanoid.entity;

import com.rpm.arkanoid.Main;

import java.awt.*;

public class Entity {

    protected Main main;

    protected double x, y;
    protected double vx, vy;

    protected int w, h;

    protected boolean dead;

    public Entity(Main main) {
        this.main = main;
    }

    public void update() {
    }

    public void render(Graphics g) {


    }

    protected void move() {
        this.x += this.vx;
        this.y += this.vy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}