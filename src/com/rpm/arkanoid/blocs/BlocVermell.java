package com.rpm.arkanoid.blocs;

import com.rpm.arkanoid.entity.EntityBlocVermell;
import com.rpm.arkanoid.entity.Pala;
import com.rpm.arkanoid.escene.Scene;

import java.awt.*;

public class BlocVermell extends Bloc{

    public BlocVermell(int x, int y){
        super(x, y, 2);
        this.c = Color.red;
    }

    public void onDie(Pala p) {
        Scene scene = p.getScene();
        EntityBlocVermell ebv = new EntityBlocVermell(p.getMain(), scene);
        ebv.setPosistion(this.x * Bloc.W, this.y * Bloc.H);
        scene.add(ebv);
    }

}
