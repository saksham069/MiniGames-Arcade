package com.miniGamesArcade.games.doodleJump;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

// DoodleJump class representing the DoodleJump game, extending the Game class
public class DoodleJump extends Game {

     // Method to start playing the DoodleJump game
    public void play() {

        // Create a new Panel for the game
        super.panel = new Panel();

        // Create a new thread for rendering
        super.renderThread = new Thread(this);

        // Call the play method from the parent class
        super.play();
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/doodleJump.jpg")).getImage();
    }
}
