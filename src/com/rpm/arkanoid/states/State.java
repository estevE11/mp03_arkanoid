package com.rpm.arkanoid.states;

import com.rpm.arkanoid.Main;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class State {

    protected Main main;

    public State(Main main) {
        this.main = main;
    }

    public void update() {

    }

    public void render(Graphics g) {

    }

    public void onKeyPressed(KeyEvent e) {

    }

    public void onKeyReleased(KeyEvent e) {

    }

}
