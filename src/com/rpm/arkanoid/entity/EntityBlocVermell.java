package com.rpm.arkanoid.entity;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.blocs.Bloc;
import com.rpm.arkanoid.escene.Scene;

import java.awt.*;

public class EntityBlocVermell extends Entity {
    public EntityBlocVermell(Main main, Scene scene) {
        super(main, scene);
    }

    public void update() {
        this.y+=5;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)this.x, (int)this.y, Bloc.W, Bloc.H);
    }
}
