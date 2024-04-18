package com.miniGamesArcade.games.simonGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Blocks implements Runnable {

    Dimension screenSize;

    // block dimensions
    private int blockWidth;
    private int blockHeight;

    // starting position to center the blocks
    private int startX;
    private int startY;

    // for block flashing and timing
    private int flashed = 0, ticks, dark;
    private int indexPattern;
    private Random random;
    private ArrayList<Integer> pattern;
    private boolean creatingPattern = true;
    private boolean gameOver;
    private boolean gameStarted;
    private int level;
    private Thread thread;
    private boolean flash;
    private int temp=0;

    Blocks() {
        level = 1;
        flash=false;
        gameStarted = false;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        blockWidth = screenSize.width / 4; // One-fourth of the screen width
        blockHeight = screenSize.height / 4; // One-fourth of the screen height

        // Calculate starting position to center the blocks
        startX = (screenSize.width - blockWidth * 2) / 2; // Half the remaining space
        startY = (screenSize.height - blockHeight * 2) / 2; // Half the remaining space
        flashed = 0;

        // to run block flashing logic
        thread = new Thread(this);
        thread.start();

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

        if (flash){
            g.setColor(Color.GREEN);
            temp++;
            if( temp==50){
                flash=false;
                temp=0;
            }
        }
        else {
            g.setColor(Color.BLACK);
        }
        g.fillRoundRect(600, 300, 300, 250, 200, 200);
        g.fillRect(startX + blockWidth - blockWidth / 10, startY, blockWidth / 6, blockHeight * 2);
        g.fillRect(startX, startY + blockWidth / 2 - blockWidth / 40, blockWidth * 2, blockHeight / 4);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(200));
        g.drawOval(startX - 100, startY - 100, blockWidth * 2 + 200, blockHeight * 2 + 200);

        if (flash){
            g.setColor(Color.GREEN);
            temp++;
            if( temp==50){
                flash=false;
                temp=0;
            }
        }
        else {
            g.setColor(Color.BLACK);
        }
        
        g.setStroke(new BasicStroke(10));
        g.drawOval(startX - 100, startY - 100, blockWidth * 2 + 200, blockHeight * 2 + 200);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 2, 56));

        if (gameOver) {
            // g.drawString("Oops \n you out.", blockWidth * 2 - 100, blockHeight * 2);
            g.drawString("Oops, ", blockWidth * 2 - 100, blockHeight * 2 -20);
            g.drawString("you out.", blockWidth * 2 - 100, blockHeight * 2 + 50); // Assuming 30 pixels vertical distance between lines
        }

        else {
            g.drawString("Level:"+ level, blockWidth * 2 - 100 , blockHeight * 2);
        }

    }

    public void start() {

        // random generator and pattern list
        random = new Random();

        // to store sequence of flashed blocks
        pattern = new ArrayList<Integer>();

    }

    @Override
    public void run() {
        

        //continues until the gameOver flag is set to true
        while (!gameOver) {
            try {

                //slight delay between iterations
                Thread.sleep(30);

                if(gameStarted){
                ticks++;   //keeps track of the number of iterations or time intervals that have passed since the game started
                // System.out.println(ticks);


                // Reset flashed block and decrease darkness level every 20 ticks
                if (ticks % 20 == 0) {
                    flashed = 0;
                    if (dark >= 0) {
                        dark--;
                    }
                }

                if (creatingPattern) {
                    if (dark <= 0) {
                        if (indexPattern >= pattern.size()) {
                            flashed = random.nextInt(40) % 4 + 1;
                            pattern.add(flashed);
                            indexPattern = 0;
                            creatingPattern = false;
                        } else {
                            flashed = pattern.get(indexPattern);
                            indexPattern++;
                        }
                        dark = 2;
                    }
                } else if (indexPattern == pattern.size()) {
                    creatingPattern = true;
                    indexPattern = 0;
                    dark = 2;
                    flash=true;
                    level++;
                }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public boolean getcreatingPattern() {
        return creatingPattern;
    }

    public int getFlashed() {
        return flashed;
    }

    public int getTicks() {
        return ticks;
    }

    public int getDark() {
        return dark;
    }

    public int getIndexPattern() {
        return indexPattern;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean getgameStarted(){
        return gameStarted;
    }

    public int getLevel(){
        return level;
    }

    public ArrayList<Integer> getPattern() {
        return pattern;
    }

    // Setter methods

    public void setFlashed(int flashed) {
        this.flashed = flashed;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void setDark(int dark) {
        this.dark = dark;
    }

    public void setIndexPattern(int indexPattern) {
        this.indexPattern = indexPattern;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setgameStarted(boolean gameStarted){
        this.gameStarted = gameStarted;
    }

}
