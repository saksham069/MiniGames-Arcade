package com.miniGamesArcade.games.flappyBird;

import java.awt.Image;

public class Pipe {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;
    private boolean passed; // Flag indicating if the bird has passed through the pipe opening

    public Pipe(Image img) {
        this.img = img;
    }

    // Method to place pipes
    public void pipesPlaced(int xPipe, int yPipe, int widthPipe, int heightPipe, int boardHeight, Image topPipeImg,
            Image bottomPipeImg) {

        //// Calculate a random Y-coordinate for the top pipe's position within the
        //// visible area of the screen
        int randomPipeY = (int) (yPipe - heightPipe / 4 - Math.random() * (heightPipe / 2));
        int openingSpace = boardHeight / 4; // space between top and bottom pipe

        // Top pipe
        x = xPipe;
        y = randomPipeY;
        width = widthPipe;
        height = heightPipe;
        passed = false;
        img = topPipeImg;

        // Bottom pipe
        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.setX(xPipe);
        bottomPipe.setY(y + height + openingSpace); // Set Y-coordinate below the top pipe and the opening space
        bottomPipe.setWidth(widthPipe);
        bottomPipe.setHeight(heightPipe);
        bottomPipe.setPassed(false);

        // Add both top and bottom pipes to the list of pipes
        Panel.pipes.add(this); // Add top pipe
        Panel.pipes.add(bottomPipe); // Add bottom pipe
    }

    // Getters and setters for private fields
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
