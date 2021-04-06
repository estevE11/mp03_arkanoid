package com.rpm.arkanoid.states;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.escene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StateGame extends State {

    private Scene scene;

    public StateGame(Main main) {
        super(main);

        this.scene = new Scene(this.main);
    }

    public void update() {
        this.scene.update();
    }

    public void render(Graphics g) {
        this.scene.render(g);
    }

    public void onKeyPressed(KeyEvent e) {
        this.scene.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e) {
        this.scene.onKeyReleased(e);
    }
}
