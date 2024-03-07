package com.example.menu;

import java.awt.Rectangle;

import com.example.main.Game;

class GameItem {
    private Rectangle gameRect;
    private Game game;
    private final int width, height;

    GameItem(int x, int y, Game g) {
        width = 200;
        height = 200;
        gameRect = new Rectangle(x, y, width, height);
        game = g;
    }

    Rectangle getRect() {
        return this.gameRect;
    }

    void start(){
        game.play(); 
    }
}
