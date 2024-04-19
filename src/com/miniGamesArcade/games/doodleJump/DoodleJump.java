package com.miniGamesArcade.games.doodleJump;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

public class DoodleJump extends Game {
    public void play() {
        super.panel = new Panel();
        super.renderThread = new Thread(this);
        super.play();
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/doodleJump.jpg")).getImage();
    }
}
