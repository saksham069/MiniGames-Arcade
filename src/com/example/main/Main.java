package com.example.main;

import javax.swing.JPanel;

import com.example.menu.Menu;

public class Main {
    public static final Window window = new Window(new JPanel());

    public static void main(String[] args) {
        // new DoodleJump();
        // window = new Window(new JPanel());
        new Menu();
    }
}
