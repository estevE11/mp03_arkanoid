package com.rpm.arkanoid.escene;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.blocs.Bloc;
import com.rpm.arkanoid.blocs.BlocBlau;
import com.rpm.arkanoid.blocs.BlocVerd;
import com.rpm.arkanoid.blocs.BlocVermell;
import com.rpm.arkanoid.entity.Ball;
import com.rpm.arkanoid.entity.Entity;
import com.rpm.arkanoid.entity.Pala;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Scene {
    private Random r = new Random();

    protected Main main;

    protected Bloc[][] blocs;
    protected LinkedList<Entity> entities;

    protected int score = 0;

    private Pala pala;
    private Ball ball;

    public Scene(Main main) {
        this.main = main;
        this.restart();
    }

    public void generateLevel() {
        this.blocs = new Bloc[Bloc.COLS][Bloc.ROWS];

        for(int y = 0; y < Bloc.ROWS; y++) {
            for(int x = 0; x < Bloc.COLS; x++) {
                int blocId = r.nextInt(3);
                switch (blocId) {
                    case 0:
                        this.blocs[x][y] = new BlocBlau(x, y);
                        break;
                    case 1:
                        this.blocs[x][y] = new BlocVermell(x, y);
                        break;
                    case 2:
                        this.blocs[x][y] = new BlocVerd(x, y);
                        break;
                }
            }
        }
    }

    public void update(){
        for (Entity e : this.entities) {
            e.update();
        }

        for(int y = 0; y < Bloc.ROWS; y++) {
            for(int x = 0; x < Bloc.COLS; x++) {
                if(this.blocs[x][y] == null) continue;
                if(this.blocs[x][y].getVida() <= 0) {
                    this.blocs[x][y].onDie(this.pala);
                    this.blocs[x][y] = null;
                    continue;
                }
                this.blocs[x][y].update();
            }
        }
    }

    public void render(Graphics g){
        for (Entity e : this.entities) {
            e.render(g);
        }

        for(int y = 0; y < Bloc.ROWS; y++) {
            for(int x = 0; x < Bloc.COLS; x++) {
                if(this.blocs[x][y] == null) continue;
                this.blocs[x][y].render(g);
            }
        }
    }

    public void restart() {
        this.entities = new LinkedList<Entity>();

        this.pala = new Pala(main, this, null);
        this.ball = new Ball(main, this, this.pala);
        this.pala.setBall(this.ball);
        this.entities.add(this.ball);
        this.entities.add(this.pala);

        this.generateLevel();
    }

    public void collidedWith(int bx, int by) {
        this.blocs[bx][by].onCollide();
    }

    public boolean blockAt(int x, int y) {
        int bx = x/Bloc.W;
        int by = y/Bloc.H;
        if(bx < 0 || bx >= Bloc.COLS || by < 0 || by >= Bloc.ROWS) return false;
        return this.blocs[bx][by] != null;
    }

    public Bloc getBlockAt(int x, int y) {
        int bx = x/Bloc.W;
        int by = y/Bloc.H;
        if(bx < 0 || bx >= Bloc.COLS || by < 0 || by >= Bloc.ROWS) return null;
        return this.blocs[bx][by];
    }

    public Bloc[][] getBlocs() {
        return this.blocs;
    }

    public void onKeyPressed(KeyEvent e)  {
        this.pala.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e)  {
        this.pala.onKeyReleased(e);
    }

}
