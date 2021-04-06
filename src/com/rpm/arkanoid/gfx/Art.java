package com.rpm.arkanoid.gfx;

import com.rpm.arkanoid.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Art {
    public static final Art i = new Art();

    public BufferedImage maintitle;

    public void load() {
        this.maintitle = this.loadImage("maintitle.png");
    }

    public BufferedImage loadImage(String path) {
        try {
            String p = "/img/" + path;
            return ImageIO.read(Main.class.getResourceAsStream(p));
        } catch (IOException e) {
            System.out.println("> " + e.getMessage());
        }
        return null;
    }
}
