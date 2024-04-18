package com.miniGamesArcade.games.brickBreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

// Class representing the paddle object in the BrickBreaker game
public class Paddle {

    // Coordinates and dimensions of the paddle
    private double x, y;
    private int paddleHeight, paddleWidth;
    private int cornerRadius = 10;

    // to get screenSize
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // constructor
    Paddle() {
        // initialising the paddle
        paddleHeight = 25;
        paddleWidth = 130;
        x = (screenSize.getWidth() - paddleWidth) / 2;
        y = screenSize.getHeight() - 50;

    }

    // Method to draw paddle
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(x, y, paddleWidth, paddleHeight, cornerRadius,
                cornerRadius);
        g.fill(roundedRect);
        g.setColor(Color.WHITE);
        g.draw(roundedRect);
    }

    // Method to update the paddle position based on mouse input
    public void setPaddlePos(int value) {
        this.x = value;

        // to stop paddle from going off the screen
        if (x > screenSize.getWidth() - paddleWidth) {
            x = screenSize.getWidth() - paddleWidth;
        }

    }

    // getters for getting properties of paddle
    public int getWidth() {
        return paddleWidth;
    }

    public int gety() {
        return (int) y;
    }

    // collidor for paddle
    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, paddleWidth, paddleHeight);
    }

}
