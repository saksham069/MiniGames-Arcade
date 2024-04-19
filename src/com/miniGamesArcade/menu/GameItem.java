package com.miniGamesArcade.menu;

import java.awt.Rectangle;

import com.miniGamesArcade.games.Game;

// Class representing an item in the main menu, which is a game
class GameItem {

    // game item variables
    private Rectangle gameRect;
    Game game;
    private final int width, height;

    // Constructor to initialize the game item with its position and associated game
    GameItem(int x, int y, Game g) {
        width = 200;
        height = 200;
        gameRect = new Rectangle(x, y, width, height);

        // Assign the associated game
        game = g;
    }

    // Method to get the rectangle representing the area occupied by the game item
    Rectangle getRect() {
        return this.gameRect;
    }
}
