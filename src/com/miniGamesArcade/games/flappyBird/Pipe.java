package com.miniGamesArcade.games.flappyBird;

import java.awt.*;

public class Pipe {
    private int xPipe;
    private int yPipe;
    private int widthPipe;

    private int heightPipe;
    private Image topPipeImg;
    private Image bottomPipeImg;
    // game board dimensions
    private int boardWidth;
    private int boardHeight;
    private boolean passed; // Flag indicating if the bird has passed through the pipe opening

    Pipe(Image topPipeImg, Image bottomPipeImg) {
        this.topPipeImg = topPipeImg;
        this.bottomPipeImg = bottomPipeImg;

        // initialize board dimensions
        boardWidth = 1600;
        boardHeight = 850;
        xPipe = boardWidth;
        yPipe = 0;
        widthPipe = 64;
        heightPipe = 512;
    }

    // Method to place pipes
    public void pipesPlaced() {

        // Calculate a random Y-coordinate for the top pipe's position within the
        // visible area of the screen
        int randomPipeY = (int) (yPipe - heightPipe / 4 - Math.random() * (heightPipe / 2));
        int openingSpace = boardHeight / 4; // space between top and bottom pipe

        Pipe topPipe = new Pipe(topPipeImg, null);
        topPipe.xPipe = boardWidth;
        topPipe.yPipe = randomPipeY;

        Panel.pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg, null);
        bottomPipe.xPipe = boardWidth;
        bottomPipe.yPipe = topPipe.yPipe + heightPipe + openingSpace;

        Panel.pipes.add(bottomPipe);

    }

    public void draw(Graphics2D g2d) {
        // Draw top pipe
        g2d.drawImage(topPipeImg, xPipe, yPipe, widthPipe, heightPipe, null);

        // Draw bottom pipe
        g2d.drawImage(bottomPipeImg, xPipe, yPipe + heightPipe + 200, widthPipe, heightPipe, null);
    }

    // Getters and setters for private fields
    public int getxPipe() {
        return xPipe;
    }

    public void setxPipe(int xPipe) {
        this.xPipe = xPipe;
    }

    public int getyPipe() {
        return yPipe;
    }

    public void setyPipe(int yPipe) {
        this.yPipe = yPipe;
    }

    public int getwidthPipe() {
        return widthPipe;
    }

    public void setWidth(int widthPipe) {
        this.widthPipe = widthPipe;
    }

    public int getheightPipe() {
        return heightPipe;
    }

    public void setheightPiope(int heightPipe) {
        this.heightPipe = heightPipe;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void setBottomPipeImg(Image bottomPipeImg) {
        this.bottomPipeImg = bottomPipeImg;
    }

    public void setTopPipeImg(Image topPipImg) {
        this.topPipeImg = topPipImg;
    }
}
