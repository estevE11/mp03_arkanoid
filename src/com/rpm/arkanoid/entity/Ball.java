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
        this.init();
    }

    public void init() {
        this.w = 14;
        this.h = 14;

        this.x = (this.pala.getX() + this.pala.getW()/2) - this.w/2;
        this.y = this.pala.getY() - this.pala.getH() - 10;

        this.vx = 0;
        this.vy = 0;
    }

    public void update() {
        this._move((int)this.vx, (int)this.vy);

        if(this.y+this.h > this.main.getHeight()) {
            this.scene.restart();
            this.scene.removeLive();
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
            double bounceDist = this.calcBounceVel();
            this.vy *= -1;
            this.vx = bounceDist*12;
        }
    }

    public void _move(int vx, int vy) {
        int xx = (int)Math.abs(vx);
        int yy = (int)Math.abs(vy);

        for(int x = 0; x < xx; x++) {
            if(!collision(oneify((int)vx), 0)) {
                this.x += oneify((int) vx);
            } else {
                this.vx *= -1;
                return;
            }
        }

        for(int y = 0; y < yy; y++) {
            if(!collision(0, oneify((int)vy))) {
                this.y += oneify((int) vy);
            } else {
                this.vy *= -1;
                return;
            }
        }
    }

    public boolean collision(double xa, double ya) {
        boolean solid = false;

        boolean col = false;

        for(int c = 0; c < 4; c++) {
            int xt = ((int)this.x + (int)xa) + c % 2 * this.w;
            int yt = ((int)this.y + (int)ya) + c / 2 * this.h;

            if(this.scene.blockAt(xt, yt)) {
                if(!col) {
                    this.scene.getBlockAt(xt, yt).onCollide();
                    col = true;
                }
                solid = true;
            }
        }

        return solid;
    }

    public int oneify(int n) {
        if(n == 0) return 0;
        return n > 0 ? 1 : -1;
    }

    private double calcBounceVel() {
        double rw = this.pala.getW()+this.w;
        double diff = this.x - this.pala.getX()+this.w;
        return (diff - rw/2)/(rw/2);
    }

    public void fire(double a) {
        this.vx = Math.cos(a)*10;
        this.vy = Math.sin(a)*10;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval((int)this.x, (int)this.y, this.w, this.h);
    }


}
