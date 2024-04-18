package com.miniGamesArcade.games.flappyBird;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

public class FlappyBird extends Game {
    public void play() {
        super.panel = new Panel(); // check if restarting makes new panel or not
        super.renderThread = new Thread(this);
        super.play();
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/flappyBird.jpg")).getImage();
    }
}
