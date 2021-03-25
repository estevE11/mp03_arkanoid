package com.rpm.arkanoid.escene;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.blocs.Bloc;
import com.rpm.arkanoid.blocs.BlocBlau;
import com.rpm.arkanoid.entity.Ball;
import com.rpm.arkanoid.entity.Entity;
import com.rpm.arkanoid.entity.Pala;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class Scene {

    protected Main main;

    protected Bloc[][] blocs;
    protected LinkedList<Entity> entities;

    protected int score = 0;

    private final Pala pala;
    private Ball ball;

    public Scene(Main main) {
        this.main = main;
        this.entities = new LinkedList<Entity>();

        this.pala = new Pala(main);
        this.ball = new Ball(main, this.pala);
        this.entities.add(this.ball);
        this.entities.add(this.pala);

        this.generateLevel();
    }

    public void generateLevel() {
        this.blocs = new Bloc[Bloc.COLS][Bloc.ROWS];

        for(int y = 0; y < this.blocs[0].length; y++) {
            for(int x = 0; x < this.blocs.length; x++) {
                this.blocs[x][y] = new BlocBlau(x, y);
            }
        }
    }

    public void update(){
        for (Entity e : this.entities) {
            e.update();
        }
    }

    public void render(Graphics g){
        for (Entity e : this.entities) {
            e.render(g);
        }

        for(int y = 0; y < this.blocs[0].length; y++) {
            for(int x = 0; x < this.blocs.length; x++) {
                Bloc b = this.blocs[x][y];
                if(b.getVida() > 0)
                    this.blocs[x][y].render(g);
            }
        }
    }

    private void checkCollisions(){

    }

    public void onKeyPressed(KeyEvent e)  {
        this.pala.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e)  {
        this.pala.onKeyReleased(e);
    }

}
