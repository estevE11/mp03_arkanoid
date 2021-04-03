package com.rpm.arkanoid.entity;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.escene.Scene;

import java.awt.*;

public class Entity {

    protected Main main;
    protected Scene scene;

    protected double x, y;
    protected double vx, vy;

    protected int w, h;

    protected boolean dead;

    public Entity(Main main, Scene scene) {
        this.main = main;
        this.scene = scene;
    }

    public void init() {

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