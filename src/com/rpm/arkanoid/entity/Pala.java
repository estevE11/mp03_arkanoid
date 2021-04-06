package com.rpm.arkanoid.entity;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.Start;
import com.rpm.arkanoid.escene.Scene;
import com.rpm.arkanoid.states.StateMenu;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Pala extends Entity {

    private Ball ball;

    protected double velocity = 13;
    private boolean inBoost = false;
    private final int maxSpeedBoostTime = 10*1000;
    private int speedBoostTime = 0;

    private final int STATE_FIRE = 0, STATE_MOVE = 1;
    private int state = 0;

    private double ball_angle = Math.PI/2;
    private double ball_angle_v = 0;

    public Pala(Main main, Scene scene, Ball ball) {
        super(main, scene);
        this.ball = ball;

        this.init();

    }

    public void init() {
        this.w = 60;
        this.h = 10;

        this.x = Start.WIDTH/2 - this.w/2;
        this.y = 630;

        this.vx = 0;
    }

    public void update() {
        if(this.state == this.STATE_MOVE) {
            this.move();

            if(this.x < 0) {
                this.x = 0;
            }
            if(this.x+this.w > this.main.getWidth()) {
                this.x = this.main.getWidth() - this.w;
            }

            if(this.inBoost) {
                this.speedBoostTime++;
                if(this.speedBoostTime > this.maxSpeedBoostTime) {
                    this.inBoost = false;
                    this.velocity = 15;
                    this.speedBoostTime = 0;
                }
            }

        } else if(this.state == this.STATE_FIRE) {
            this.ball_angle += this.ball_angle_v;
            if(this.ball_angle < -Math.PI/2-Math.PI/4) this.ball_angle = -Math.PI/2-Math.PI/4;
            if(this.ball_angle > 0-Math.PI/4) this.ball_angle = 0-Math.PI/4;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int)this.x, (int)this.y, this.w, this.h);

        if(this.state == this.STATE_FIRE) {
            int ix = (int)(this.x + this.w/2);
            int iy = (int)this.y;
            ((Graphics2D) g).setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.setColor(Color.WHITE);
            g.drawLine(ix, iy, ix + (int)(Math.cos(this.ball_angle)*30), iy + (int)(Math.sin(this.ball_angle)*30));
        }
    }

    private void fire() {
        this.ball.fire(this.ball_angle);
        this.state = this.STATE_MOVE;
    }

    public void giveSpeedBoost(){
        this.inBoost = true;
        this.speedBoostTime = 0;
        this.velocity = 18;
    }

    public void collision(Entity other) {
        if(other instanceof EntityBlocVermell) {
            this.main.setState(new StateMenu(this.main));
        }
    }

    public void onKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(this.state == this.STATE_MOVE) {
            if(keyCode == KeyEvent.VK_RIGHT) {
                this.vx = this.velocity;
            }
            if (keyCode == KeyEvent.VK_LEFT){
                this.vx = -this.velocity;
            }
        } else if(this.state == this.STATE_FIRE) {
            if(keyCode == KeyEvent.VK_RIGHT) {
                this.ball_angle_v = .05;
            }
            if (keyCode == KeyEvent.VK_LEFT){
                this.ball_angle_v = -.05;
            }

            if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP) this.fire();
        }
    }

    public void onKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(this.state == this.STATE_MOVE) {
            if(keyCode == KeyEvent.VK_RIGHT) {
                this.vx = 0;
            }
            if (keyCode == KeyEvent.VK_LEFT){
                this.vx = 0;
            }
        } else if(this.state == this.STATE_FIRE) {
            if(keyCode == KeyEvent.VK_RIGHT) {
                this.ball_angle_v = 0;
            }
            if (keyCode == KeyEvent.VK_LEFT){
                this.ball_angle_v = 0;
            }
        }
    }

    public Scene getScene() {
        return this.scene;
    }

    public Main getMain() {
        return this.main;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
