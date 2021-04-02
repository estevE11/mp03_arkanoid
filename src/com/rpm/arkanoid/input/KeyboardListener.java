package com.rpm.arkanoid.input;

import com.rpm.arkanoid.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Main main;

    public KeyboardListener(Main main) {
        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.main.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.main.keyReleased(e);
    }
}
