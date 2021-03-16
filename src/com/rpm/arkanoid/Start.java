package com.rpm.arkanoid;

import javax.swing.*;
import java.awt.*;

public class Start {

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

        frame.setVisible(true);

        main.start();
    }
}
