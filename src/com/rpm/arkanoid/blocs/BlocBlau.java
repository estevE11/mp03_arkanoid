package com.rpm.arkanoid.blocs;

import java.awt.*;

public class BlocBlau extends Bloc{

    protected int maxVida = 2;

    public BlocBlau(int x, int y){
        super(x, y, 3);
        this.c = Color.BLUE;
    }
}
