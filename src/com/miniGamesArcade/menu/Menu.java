package com.miniGamesArcade.menu;

import com.miniGamesArcade.main.Main;

public class Menu {
    private Panel panel;

    public Menu() {
        panel = new Panel();
        Main.window.setPanel(panel);
        panel.requestFocus();
    }
}
