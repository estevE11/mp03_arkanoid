package com.rpm.arkanoid.blocs;

import com.rpm.arkanoid.entity.Pala;

public class Bloc {

    protected int x, y;

    protected int vida;
    protected int maxVida;

    public Bloc() {
        this.vida = this.maxVida;
    }

    public void render(){

    }

    public void onDie(Pala p){

    }
}
