package com.rpm.arkanoid;

import com.rpm.arkanoid.escene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {
    private Thread mainThread;
    private Scene scene;

    private boolean running = true;

    private void init() {
        this.requestFocus();
        this.scene = new Scene(this);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                scene.onKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                scene.onKeyReleased(e);
            }
        });
    }

    public void run() {
        this.init();

        while(this.running) {
            this.update();
            this.render();
        }
    }

    public void update() {
        this.scene.update();
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

        this.scene.render(g);

        g.dispose();
        bs.show();
    }

    public void start() {
        this.mainThread = new Thread(this, "GameThread");
        this.mainThread.start();
    }
}