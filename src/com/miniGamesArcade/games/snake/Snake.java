package com.miniGamesArcade.games.snake;

import javax.swing.ImageIcon;

import com.miniGamesArcade.games.Game;

// Snake class representing the Snake game, extending the Game class
public class Snake extends Game {

    // Method to start playing the Snake game
    public void play() {
        // Create a new Panel for the game
        super.panel = new Panel(); 

        // Create a new thread for rendering
        super.renderThread = new Thread(this); 

        // Call the play method from the parent class
        super.play(); 
    }

    {
        super.icon = new ImageIcon(getClass().getResource("res/snake.jpg")).getImage();
    }
}
