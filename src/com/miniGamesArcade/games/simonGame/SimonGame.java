package com.miniGamesArcade.games.simonGame;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

public class SimonGame extends Game {
    public void play() {
        super.panel = new Panel(); // check if restarting makes new panel or not
        super.renderThread = new Thread(this);
        super.play();
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/simonGame.jpg")).getImage();
    }
}