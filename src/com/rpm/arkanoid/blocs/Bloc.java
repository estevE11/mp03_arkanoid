package com.rpm.arkanoid.blocs;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.Start;
import com.rpm.arkanoid.entity.Pala;

import java.awt.*;

public class Bloc {
    public static final int ROWS = 5, COLS = 15;
    public static final int W = Start.WIDTH/COLS, H = 30;

    protected int x, y;

    protected Color c;

    protected int vida;
    protected int maxVida;

    private int border = 2;

    private boolean onDieAnimation = false;
    private int onDieAnimTime = 0;
    private final int onDieAnimMaxTime = 15;


    public Bloc(int x, int y, int maxVida) {
        this.x = x;
        this.y = y;
        this.vida = maxVida;
    }

    public void update() {
        if(this.onDieAnimation) {
            this.onDieAnimTime++;
            if(this.onDieAnimMaxTime < this.onDieAnimTime) {
                this.onDieAnimation = false;
                this.onDieAnimTime = 0;
            }
        }
    }

    public void render(Graphics g) {
        int x = this.x*W + this.border;
        int y = this.y*H + this.border;
        int w = W-this.border/2;
        int h = H-this.border/2;
        g.setColor(this.c);
        g.fillRect(x, y, w, h);

        if (this.onDieAnimation) {
            g.setColor(Color.black);
            g.fillRect(x, y + (h*this.onDieAnimTime/this.onDieAnimMaxTime), w, 10);
        }
    }

    public void onCollide() {
        this.vida--;
        this.onDieAnimation = true;
        System.out.println(this.vida);
    }

    public void onDie(Pala p){

    }

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
}

