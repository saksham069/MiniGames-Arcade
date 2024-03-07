package com.example.menu;

import com.example.main.Main;

public class Menu {
    private Panel panel;

    public Menu() {
        panel = new Panel();
        Main.window.setPanel(panel);
        panel.requestFocus();
    }
}
