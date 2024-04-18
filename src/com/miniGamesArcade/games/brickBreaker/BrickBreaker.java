package com.miniGamesArcade.games.brickBreaker;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

public class BrickBreaker extends Game {
    public void play() {
        super.panel = new Panel(); // check if restarting makes new panel or not
        super.renderThread = new Thread(this);
        super.play();
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/brickBreaker.jpg")).getImage();
    }
}
