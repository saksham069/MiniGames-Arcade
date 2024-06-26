package com.miniGamesArcade.games.brickBreaker;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

// BrickBreaker class representing the BrickBreaker game, extending the Game class
public class BrickBreaker extends Game {

    // Method to start playing the brickBreaker game
    public void play() {

        // Create a new Panel for the game
        super.panel = new Panel();

        // Create a new thread for rendering
        super.renderThread = new Thread(this);

        // Call the play method from the parent class
        super.play();
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/brickBreaker.jpg")).getImage();
    }
}
