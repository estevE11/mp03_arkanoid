package com.rpm.arkanoid;

import javax.swing.*;
import java.awt.*;

public class Start {
    public static final int WIDTH = 640, HEIGHT = 480;


    public static void main(String args[]) {
        Main main = new Main();
        JFrame frame = new JFrame();

        frame.setPreferredSize(new Dimension(640, 480));
        frame.setMaximumSize(new Dimension(640, 480));
        frame.setMinimumSize(new Dimension(640, 480));

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(main);

        frame.requestFocus();

        frame.setVisible(true);

        main.start();
    }
}
