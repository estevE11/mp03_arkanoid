package com.rpm.arkanoid.blocs;

import com.rpm.arkanoid.entity.Pala;

import java.awt.*;

public class BlocBlau extends Bloc{

    public BlocBlau(int x, int y){
        super(x, y, 1);
        this.c = Color.BLUE;
    }

    public void onDie(Pala p) {
        p.giveSpeedBoost();
    }
}
