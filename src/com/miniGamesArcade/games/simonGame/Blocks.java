package com.miniGamesArcade.games.simonGame;

import java.awt.*;


public class Blocks {

    Dimension screenSize;

    // block dimensions
    private int blockWidth;
    private int blockHeight;

    // starting position to center the blocks
    private int startX;
    private int startY;

    // for block flashing and timing
    private int flashed = 0;

    private boolean flash;

    private boolean gameOver;

    private int level;

    private int temp;

    Blocks() {
        level = 1;

        temp = 0;

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        blockWidth = screenSize.width / 4; // One-fourth of the screen width
        blockHeight = screenSize.height / 4; // One-fourth of the screen height

        // Calculate starting position to center the blocks
        startX = (screenSize.width - blockWidth * 2) / 2; // Half the remaining space
        startY = (screenSize.height - blockHeight * 2) / 2; // Half the remaining space
        flashed = 0;

    }

    public void draw(Graphics2D g) {

        // for blocks
        if (flashed == 1) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.GREEN.darker());
        }
        g.fillRect(startX, startY, blockWidth, blockHeight);

        if (flashed == 2) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.red.darker());
        }
        g.fillRect(startX + blockWidth, startY, blockWidth, blockHeight);

        if (flashed == 3) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.orange.darker());
        }
        g.fillRect(startX, startY + blockHeight, blockWidth, blockHeight);

        if (flashed == 4) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.blue.darker());
        }
        g.fillRect(startX + blockWidth, startY + blockHeight, blockWidth, blockHeight);

        if (flash) {
            g.setColor(Color.CYAN);
            temp++;

            if (temp == 50) {
                flash = false;
                temp = 0;
            }
        } else {
            g.setColor(Color.black);
        }

        g.fillRoundRect(600, 300, 300, 250, 200, 200);

        g.fillRect(startX + blockWidth - blockWidth / 10, startY, blockWidth / 6, blockHeight * 2);
        g.fillRect(startX, startY + blockWidth / 2 - blockWidth / 40, blockWidth * 2, blockHeight / 4);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(200));
        g.drawOval(startX - 100, startY - 100, blockWidth * 2 + 200, blockHeight * 2 + 200);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(10));
        g.drawOval(startX - 100, startY - 100, blockWidth * 2 + 200, blockHeight * 2 + 200);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 2, 56));

        if (gameOver) {
            
            g.drawString("Oops, ", blockWidth * 2 - 100, blockHeight * 2 - 20);
            g.drawString("you out.", blockWidth * 2 - 100, blockHeight * 2 + 50); 
        }

        else {
            g.drawString("Level:" + level, blockWidth * 2 - 100, blockHeight * 2);
        }

    }

    public int getblockHeight() {
        return blockHeight;
    }

    public int getblockWidth() {
        return blockWidth;
    }

    public int getstartX() {
        return startX;
    }

    public int getstartY() {
        return startY;
    }

    public int getFlashed() {
        return flashed;
    }

    public boolean getFlash() {
        return flash;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Setter methods

    public void setFlashed(int flashed) {
        this.flashed = flashed;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }

}
