package com.miniGamesArcade.games.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

// Class representing the snake object in the Snake game
public class SnakeObj {
    // Dimension object to get the screensize
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

    //number and size of blocks based on screensize
    private int blockSize; 
    private int noOfblocks; 

    // Array to store x and y-coordinate of each block
    private int[] x;
    private int[] y; 

    // Initial number of body parts
    private int bodyParts; 

    // Constructor
    SnakeObj() {
        blockSize=30;
        bodyParts=6;
        noOfblocks = (int) (screenSize.getWidth() * screenSize.getHeight()) / (blockSize * blockSize);
        x = new int[noOfblocks];
        y = new int[noOfblocks];
    }

    // Method to draw the snake on the screen
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g2d.setColor(Color.green); 
                g2d.fillRect(x[i], y[i], blockSize, blockSize);
            } else {
                g2d.setColor(new Color(45, 180, 0)); 
                g2d.fillOval(x[i], y[i], blockSize, blockSize); 
            }
        }
    }

    // Method to update the snake's position based on direction
    public void update(int value) {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (value) {
            case 1:
                changeRight();
                break;
            case 2:
                changeLeft();
                break;
            case 3:
                changeUp();
                break;
            case 4:
                changeDown();
                break;
            default:
                break;
        }
    }

    // Method to get the rectangle representing the snake's head for collision detection
    Rectangle getRectHead() {
        return new Rectangle(x[0], y[0], blockSize, blockSize);
    }

    // Method to check for collisions with the snake's own body
    public boolean checkBodyCollisions() {

        // Check if head collides with any body part
        for (int i = bodyParts; i > 1; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                return false; 
            }
        }
        return true; 
    }

    // Methods to change the direction of the snake's movement
    public void changeUp() {
        y[0] = y[0] - blockSize;
    }

    public void changeDown() {
        y[0] = y[0] + blockSize;
    }

    public void changeLeft() {
        x[0] = x[0] - blockSize;
    }

    public void changeRight() {
        x[0] = x[0] + blockSize;
    }

    // Method to increase the number of body parts when snake eats food
    public void setBodyParts() {
        bodyParts += 1;
    }

    // Method to check for collisions with the borders of the screen 
    public void checkBorderCollisions() {
        if (x[0] < 0) {
            x[0] = (int) screenSize.getWidth();
        }
        if (x[0] > screenSize.getWidth()) {
            x[0] = 0;
        }
        if (y[0] < 0) {
            y[0] = (int) screenSize.getHeight();
        }
        if (y[0] > screenSize.getHeight()) {
            y[0] = 0;
        }
    }
}
