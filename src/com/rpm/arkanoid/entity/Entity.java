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

    public void collision(Entity other) {

    }

    protected void move() {
        this.x += this.vx;
        this.y += this.vy;
    }

    public static boolean areColliding(Entity e0, Entity e1) {
        return e0.getX() + e0.getW() > e1.getX() && e0.getX() < e1.getX() + e1.getW() && e0.getY() + e0.getH() > e1.getY() && e0.getY() < e1.getY() + e1.getH();
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

    public void setPosistion(int x, int y) {
        this.x = x;
        this.y = y;
    }
}