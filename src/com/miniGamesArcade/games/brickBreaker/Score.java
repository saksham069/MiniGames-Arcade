package com.miniGamesArcade.games.brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

// Class representing the score in the Brick Breaker game
public class Score {

    // Variable to store the score
    private int score;

    // Constructor to initialize the score to zero
    public Score() {
        score = 0;
    }

    // Method to draw the score on the screen
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 36);
        g.setFont(font);
        g.drawString("Score: " + score, 40, 40);
    }

    // Method to get the current score
    public int getScore() {
        return score;
    }

    // Method to add points to the score
    public void addScore(int value) {
        score += value;
    }
}
