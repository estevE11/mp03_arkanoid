package com.rpm.arkanoid.states;

import com.rpm.arkanoid.Main;
import com.rpm.arkanoid.gfx.Art;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StateMenu extends State {

    private String[] menu_items = new String[]{"Play", "Exit"};
    private int selected = 0;

    public StateMenu(Main main) {
        super(main);
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.drawImage(Art.i.maintitle, 150, 100, 1280/3, 465/3, null);

        for(int i = 0; i < this.menu_items.length; i++) {
            g.setColor(i == selected ? Color.yellow : Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 38));
            g.drawString(this.menu_items[i], 160, 300+i*40);
        }

        String creds = "Fet per Roger Esteve, Marti Corbalan i Pol Pujadó";
        g.setColor(Color.lightGray);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(creds, 160, 570);
    }

    private void itemSelected(int i) {
        switch (i) {
            case 0 -> this.main.setState(new StateGame(this.main));
            case 1 -> System.exit(1);
        }
    }

    private void moveSelector(int steps) {
        this.selected += steps;

        if(this.selected < 0) {
            this.selected = this.menu_items.length-1;
        } else if(this.selected > this.menu_items.length-1) {
            this.selected = 0;
        }
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            this.moveSelector(-1);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.moveSelector(-1);
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.itemSelected(this.selected);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        super.onKeyReleased(e);
    }
}
