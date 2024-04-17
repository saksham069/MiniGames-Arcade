package com.miniGamesArcade.main;

import javax.swing.JPanel;

import com.miniGamesArcade.menu.Menu;

public class Main {
    public static final Window window = new Window(new JPanel());

    public static void main(String[] args) {
        new Menu();
    }
}
