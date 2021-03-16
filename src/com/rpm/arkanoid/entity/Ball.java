package com.rpm.arkanoid.entity;

import com.rpm.arkanoid.Main;

import java.awt.*;

public class Ball extends Entity{
    private Pala pala;

    protected double velocity;

    public Ball(Main main, Pala pala) {
        super(main);
        this.pala = pala;

        this.vx = .1;
        this.vy = .1;

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
            this.vy *= -1;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)this.x, (int)this.y, this.w, this.h);
    }


}
