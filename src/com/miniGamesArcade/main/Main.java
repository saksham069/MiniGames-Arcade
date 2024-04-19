package com.miniGamesArcade.main;

import javax.swing.JPanel;

import com.miniGamesArcade.menu.Menu;

// Main class responsible for initializing the game window and starting the menu
public class Main {

    // Static variable representing the game window
    public static final Window window = new Window(new JPanel());

    // Main method, entry point of the application
    public static void main(String[] args) {
        // Initialize the menu
        new Menu();
    }
}
