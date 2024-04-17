package com.miniGamesArcade.games.snake;

import com.miniGamesArcade.games.Game;

public class Snake extends Game {
    public void play() {
        super.panel = new Panel();
        super.renderThread = new Thread(this);
        super.play();
    }
}
