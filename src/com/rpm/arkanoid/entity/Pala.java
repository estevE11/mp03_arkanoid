package com.rpm.arkanoid.entity;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.escene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pala extends Entity {

    protected double velocity = 15;

    public Pala(Main main, Scene scene) {
        super(main, scene);

        this.y = 630;

        this.vx = 0;

        this.w = 60;
        this.h = 10;
    }

    public void update() {
        this.move();

        if(this.x < 0) {
            this.x = 0;
        }
        if(this.x+this.w > this.main.getWidth()) {
            this.x = this.main.getWidth() - this.w;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)this.x, (int)this.y, this.w, this.h);
    }

    protected void giveSpeedBoost(){

    }

    public void onKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_RIGHT) {
            this.vx = this.velocity;
        }
        if (keyCode == KeyEvent.VK_LEFT){
            this.vx = -this.velocity;
        }
    }

    public void onKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_RIGHT) {
            this.vx = 0;
        }
        if (keyCode == KeyEvent.VK_LEFT){
            this.vx = 0;
        }
    }
}
