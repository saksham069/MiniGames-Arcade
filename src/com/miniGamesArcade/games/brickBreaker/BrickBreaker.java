package com.miniGamesArcade.games.brickBreaker;

import com.miniGamesArcade.games.Game;

public class BrickBreaker extends Game {
    public void play() {
        super.panel = new Panel(); // check if restarting makes new panel or not
        super.renderThread = new Thread(this);
        super.play();
    }
}
