package com.miniGamesArcade.menu;

import com.miniGamesArcade.main.Main;

// Class representing the game menu
public class Menu {
    
    // Instance of the menu panel
    private Panel panel; 

    // Constructor to initialize the menu
    public Menu() {
        // Create ad set a new instance of the menu panel that is defined in the menu class 
        panel = new Panel(); 
        Main.window.setPanel(panel); 
        panel.requestFocus(); }
}