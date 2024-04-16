package com.example.snake;

import com.example.main.Game;

public class Snake extends Game {
    public void play() {
        super.panel = new Panel();
        super.renderThread = new Thread(this);
        super.play();
    }
}
