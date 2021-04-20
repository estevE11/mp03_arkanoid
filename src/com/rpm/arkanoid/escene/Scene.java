package com.rpm.arkanoid.escene;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.blocs.Bloc;
import com.rpm.arkanoid.blocs.BlocBlau;
import com.rpm.arkanoid.blocs.BlocVerd;
import com.rpm.arkanoid.blocs.BlocVermell;
import com.rpm.arkanoid.entity.Ball;
import com.rpm.arkanoid.entity.Entity;
import com.rpm.arkanoid.entity.Pala;
import com.rpm.arkanoid.states.StateMenu;
import com.rpm.arkanoid.utils.FileUtils;

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
    protected int hiscore = 0;

    private Pala pala;
    private Ball ball;

    private int lives = 3;

    public Scene(Main main) {
        this.main = main;
        this.loadScore();
        this.restart();
        this.generateLevel();
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
            for (Entity other : this.entities) {
                if(e != other) {
                    if(Entity.areColliding(e, other)) {
                        e.collision(other);
                    }
                }
            }
        }

        for(int y = 0; y < Bloc.ROWS; y++) {
            for(int x = 0; x < Bloc.COLS; x++) {
                if(this.blocs[x][y] == null) continue;
                if(this.blocs[x][y].getVida() <= 0) {
                    this.blocs[x][y].onDie(this.pala);
                    this.score += 250;
                    if(this.score > this.hiscore) this.hiscore = this.score;
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

        for(int i = 0; i < this.lives; i++) {
            g.setColor(Color.red);
            g.fillRect(10 + i*15, 660, 10, 20);
        }

        g.setColor(Color.white);
        g.setColor(Color.lightGray);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + this.score, 580, 660);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("High Score: " + this.hiscore, 580, 675);
    }


    public void restart() {
        this.entities = new LinkedList<Entity>();

        this.pala = new Pala(main, this, null);
        this.ball = new Ball(main, this, this.pala);
        this.pala.setBall(this.ball);
        this.entities.add(this.ball);
        this.entities.add(this.pala);

        //this.generateLevel();
    }

    public void add(Entity e) {
        this.entities.add(e);
    }

    public void collidedWith(int bx, int by) {
        this.blocs[bx][by].onCollide();
    }

    private void loadScore() {
        this.hiscore = Integer.parseInt(FileUtils.read_file_by_line("res/saves/highscore.sav"));
        System.out.println("Loaded highscore" + this.hiscore);
    }

    private void saveScore() {
        FileUtils.write_file(this.hiscore+"", "res/saves/highscore.sav");
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

    public void removeLive() {
        this.lives--;
        if(this.lives <= 0) {
            this.main.setState(new StateMenu(this.main));
            this.saveScore();
        }
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

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void getScore(int score) {
        this.score += score;
    }

}
