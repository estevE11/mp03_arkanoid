package com.rpm.arkanoid;

import com.rpm.arkanoid.escene.Scene;
import com.rpm.arkanoid.gfx.Art;
import com.rpm.arkanoid.input.KeyboardListener;
import com.rpm.arkanoid.states.State;
import com.rpm.arkanoid.states.StateGame;
import com.rpm.arkanoid.states.StateMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {
    private Thread mainThread;
    private State state;
    private KeyboardListener kb;

    private boolean running = true;

    private int fps = 0, ups = 0;

    private void init() {
        this.requestFocus();
        this.state = new StateMenu(this);
        this.kb = new KeyboardListener(this);

        this.addKeyListener(this.kb);

        Art.i.load();
    }

    public void run() {
        this.init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                update();
                ups++;
                delta--;
            }
            render();
            fps++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + fps + " UPS: " + ups);
                ups = 0;
                fps = 0;
            }
        }
    }

    public void update() {
        this.state.update();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.state.render(g);

        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        this.state.onKeyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        this.state.onKeyReleased(e);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() {
        this.mainThread = new Thread(this, "GameThread");
        this.mainThread.start();
    }
}