package com.miniGamesArcade.games.doodleJump;

import com.miniGamesArcade.games.Game;

public class DoodleJump extends Game {
    public void play() {
        super.panel = new Panel();
        super.renderThread = new Thread(this);
        super.play();
    }
}
