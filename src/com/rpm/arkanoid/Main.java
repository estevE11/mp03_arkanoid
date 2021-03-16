package com.rpm.arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {
    private Thread mainThread;

    private boolean running = true;

    public void run() {
        while(this.running) {
            this.update();
            this.render();
        }
    }

    public void update() {
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


        g.dispose();
        bs.show();
    }

    public void start() {
        this.mainThread = new Thread(this, "GameThread");
        this.mainThread.start();
    }
}