package com.miniGamesArcade.games.brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

// Class representing the map object in the brickBreaker game
public class Map {

    // 2d array for bricks
    private int[][] map;

    // Height and width of each brick
    private double brickHeight, brickWidth;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Padding for positioning the bricks
    public final int paddingX = 80, paddingY = 100;

    // Constructor to initialize the map
    public Map(int row, int col) {
        map = new int[row][col];

        // alloting a random value btw 0-5 for each brick which is later used to provide
        // random colors to bricks
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int r = (int) (Math.random() * 5 + 1);
                map[i][j] = r;
            }
        }

        // initialising the paddle's dimensions
        brickHeight = (screenSize.getHeight() / 2 - paddingY * 1.5) / row;
        brickWidth = (screenSize.getWidth() - 2 * paddingX) / col;

    }

    // Method to draw the bricks on the screen
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    if (map[i][j] == 1) {
                        g.setColor(Color.CYAN);
                    }

                    if (map[i][j] == 2) {
                        g.setColor(Color.RED);
                    }

                    if (map[i][j] == 3) {
                        g.setColor(Color.YELLOW);
                    }

                    if (map[i][j] == 4) {
                        g.setColor(Color.PINK);
                    }

                    if (map[i][j] == 5) {
                        g.setColor(Color.GREEN);
                    }

                    g.fillRect((int) (j * brickWidth + paddingX), (int) (i * brickHeight + paddingY), (int) brickWidth,
                            (int) brickHeight);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.white);
                    g.drawRect((int) (j * brickWidth + paddingX), (int) (i * brickHeight + paddingY), (int) brickWidth,
                            (int) brickHeight);
                }

            }
        }
    }

    // to get the map array
    public int[][] getMapArray() {
        return map;
    }

    // Method to mark a brick as hit at the specified row and column
    public void hitBrick(int row, int col) {
        map[row][col] = 0;
    }

    // to get the width of each brick
    public double getBrickWidth() {
        return brickWidth;
    }

    // to get the height of each brick
    public double getBrickHeight() {
        return brickHeight;
    }

    // Method to check if all bricks are destroyed, indicating a win
    public boolean isWin() {
        boolean isWin = false;
        int bricksRemaining = 0;

        // Count the remaining bricks on the map
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                bricksRemaining += map[i][j];
            }
        }

        // If there are no remaining bricks, set isWin to true
        if (bricksRemaining == 0) {
            isWin = true;
        }
        return isWin;
    }
}