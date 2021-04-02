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

    public Bloc(int x, int y, int maxVida) {
        this.x = x;
        this.y = y;
        this.vida = maxVida;
    }

    public void render(Graphics g) {
        g.setColor(this.c);
        g.fillRect(this.x*this.W + this.border, this.y*this.H + this.border, W-this.border/2, H-this.border/2);
    }

    public void onCollide() {
        System.out.println("collided");
        this.vida = 0;
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

