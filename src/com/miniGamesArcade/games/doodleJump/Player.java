package com.miniGamesArcade.games.doodleJump;

import java.awt.Rectangle;

//Represents the player in the Doodle Jump game.
class Player {

    // Represents the frame of the player
    Rectangle frame;

    // Represents the collider area of the player
    Rectangle collider;

    // Constructs and initialises a player object
    Player(int x, int y, int w, int h) {
        frame = new Rectangle(x, y, w, h);
        collider = new Rectangle(x, y + 9 * h / 10, w, h / 10);
    }

    // Set the position and size of the player's frame and collidor
    void setPos(double x, double y, double w, double h) {
        frame.setFrame(x, y, w, h);
        collider.setFrame(x, y + 9 * h / 10, w, h / 10);
    }
}
