package com.miniGamesArcade.games.doodleJump;

import com.miniGamesArcade.games.Game;

public class DoodleJump extends Game {
    public void play() {
        super.panel = new Panel(); // check if restarting makes new panel or not
        super.renderThread = new Thread(this);
        super.play();
    }
}
