package com.miniGamesArcade.games.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;

// Class representing the score in the Snake game
public class Score {
    private int score;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Constructor to initialize the score to zero
    Score() {
        score = 0;
    }

    // Method to draw the score on the screen
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 36);
        g.setFont(font);
        g.drawString("Score: " + score, (int) screenSize.getWidth() - 180, 40);
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
