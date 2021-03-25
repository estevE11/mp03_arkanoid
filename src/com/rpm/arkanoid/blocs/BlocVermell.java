package com.rpm.arkanoid.blocs;

import java.awt.*;

public class BlocVermell extends Bloc{

    protected int maxVida = 2;

    public BlocVermell(int x, int y){
        super(x, y, 3);
        this.c = Color.red;
    }

}
