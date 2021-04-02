package com.rpm.arkanoid.entity;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.blocs.Bloc;
import com.rpm.arkanoid.escene.Scene;

import java.awt.*;

public class Ball extends Entity{
    private Pala pala;

    protected double velocity;

    public Ball(Main main, Scene scene, Pala pala) {
        super(main, scene);
        this.pala = pala;

        this.vx = 5;
        this.vy = 5;

        this.w = 16;
        this.h = 16;
    }

    public void update() {
        this.move();

        if(this.y+this.h > this.main.getHeight()) {
            this.vy *= -1;
        }
        if(this.x+this.w > this.main.getWidth()) {
            this.vx *= -1;
        }
        if(this.y < 0) {
            this.vy *= -1;
        }
        if(this.x < 0) {
            this.vx *= -1;
        }

        if(this.y + this.h > this.pala.getY() && this.y < this.pala.getY() + this.pala.getH() && this.x + this.w > this.pala.getX() && this.x < this.pala.getX() + this.pala.getW()) {
            this.y = this.pala.getY() - this.h -1;
            double bounceDist = calcBounceVel();
            this.vy *= -1;
            this.vx = bounceDist*12;
        }
    }

    private double calcBounceVel() {
        double rw = this.pala.getW()+this.w;
        double diff = this.x - this.pala.getX()+this.w;
        return (diff - rw/2)/(rw/2);
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)this.x, (int)this.y, this.w, this.h);
    }


}
