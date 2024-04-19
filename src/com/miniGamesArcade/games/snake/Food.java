package com.miniGamesArcade.games.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Rectangle;

// Class representing the food object in the Snake game
public class Food {

    // Declaring food instance variables
    private int foodSize; 
    private int foodX; 
    private int foodY; 

    // Declaring random object for generating random positions
    private Random random;

    // Dimension object to get the screen size
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Constructor to initialize the food object
    Food() {
        // Initializing random object
        random = new Random();

        // Initializing food size
        foodSize = 30;

        // Generating random initial position for food
        foodX = random.nextInt((int) screenSize.getWidth() / foodSize) * foodSize;
        foodY = random.nextInt((int) screenSize.getHeight() / foodSize) * foodSize;
    }

    // Method to render the food on the screen
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED); 
        g2d.fillRect(foodX, foodY, foodSize, foodSize); 
    }

    // Method to get a rectangle representing the food for collision detection
    public Rectangle getRect() {
        return new Rectangle(foodX, foodY, foodSize, foodSize); 
    }

    // Method to respawn food at a random location on the screen
    public void foodReSpawn() {
        foodX = random.nextInt((int) screenSize.getWidth() / foodSize) * foodSize;
        foodY = random.nextInt((int) screenSize.getHeight() / foodSize) * foodSize;
    }
}
