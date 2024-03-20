package com.example.flappyBird;

import com.example.main.Game;

public class FlappyBird extends Game {
    public void play() {
        super.panel = new Panel(); // check if restarting makes new panel or not
        super.renderThread = new Thread(this);
        super.play();
        // panel.requestFocus();
    }
}
