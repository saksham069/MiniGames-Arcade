package com.miniGamesArcade.menu;

import java.awt.Rectangle;

import com.miniGamesArcade.games.Game;

class GameItem {
    private Rectangle gameRect;
    Game game;
    private final int width, height;

    GameItem(int x, int y, Game g) {
        width = 200;
        height = 200;
        gameRect = new Rectangle(x, y, width, height);
        game = g;
    }

    Rectangle getRect() {
        return this.gameRect; // maybe dont use getter?
    }
}
