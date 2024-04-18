package com.miniGamesArcade.games.flappyBird;

import java.awt.Image;

public class Pipe {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;
    private boolean passed;

    public Pipe(Image img) {
        this.img = img;
    }

    public void pipesPlaced(int xPipe, int yPipe, int widthPipe, int heightPipe, int boardHeight, Image topPipeImg, Image bottomPipeImg) {
        int randomPipeY = (int) (yPipe - heightPipe / 4 - Math.random() * (heightPipe / 2));
        int openingSpace = boardHeight / 4;

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
        bottomPipe.setY(y + height + openingSpace);
        bottomPipe.setWidth(widthPipe);
        bottomPipe.setHeight(heightPipe);
        bottomPipe.setPassed(false);

        // Add pipes to the list
        Panel.pipes.add(this);
        Panel.pipes.add(bottomPipe);
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
