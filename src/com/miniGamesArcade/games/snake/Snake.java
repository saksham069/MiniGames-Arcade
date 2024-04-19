package com.miniGamesArcade.games.snake;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

public class Snake extends Game {
    public void play() {
        super.panel = new Panel();
        super.renderThread = new Thread(this);
        super.play();
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/snake.jpg")).getImage();
    }
}
