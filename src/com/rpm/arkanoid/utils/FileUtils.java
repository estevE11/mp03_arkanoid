package com.rpm.arkanoid.utils;

import com.rpm.arkanoid.Main;

import javax.swing.*;
import java.io.*;

public class FileUtils {
    public static void create_file(String path) {
        File f = new File(path);
        try {
            if(f.createNewFile()) {
                System.out.println("File created succesfully!");
            } else {
                JOptionPane.showMessageDialog(null, "File already exists!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write_file(String str, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(str);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String read_file_by_line(String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader reader = new BufferedReader(fr);
            String str = "";
            String line;

            while((line = reader.readLine()) != null) {
                str += line;
            }

            fr.close();
            reader.close();
            return str;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Files required not found!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read the files!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
