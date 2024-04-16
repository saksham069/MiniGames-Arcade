package com.example.simonGame;

import com.example.main.Game;

public class SimonGame extends Game {
    public void play() {
        super.panel = new Panel(); // check if restarting makes new panel or not
        super.renderThread = new Thread(this);
        super.play();
    }
}