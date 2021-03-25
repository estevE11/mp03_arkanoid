package com.rpm.arkanoid.blocs;

import java.awt.*;

public class BlocVerd extends Bloc{

    protected int maxVida = 3;

    public BlocVerd(int x, int y){
        super(x, y, 3);
        this.c = Color.green;
    }
}
