package com.example.flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //to store all the pipes
import java.util.Random;
import javax.swing.*;

class Panel extends JPanel {

    private int birdX = 650;
    private int birdY = 300;
    private int velocityX = -4; // moves pipes to the left speed
    private int velocityY = 0;
    private int gravity = 1;

    // Images : these 4 variables will store our image objects
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int boardWidth = 930;
    int boardHeight = 850;

    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;

        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    // Pipes
    int xPipe = boardWidth;
    int yPipe = 0;
    int widthPipe = 64;
    int heightPipe = 512;

    class Pipe {
        int x = xPipe;
        int y = yPipe;
        int width = widthPipe;
        int height = heightPipe;
        Image img;
        boolean passed = false; // to check if our flappy bird has passed the pipe yet?

        Pipe(Image img) {
            this.img = img;
        }
    }

    Bird bird;
    ArrayList<Pipe> pipes;
    Random random = new Random();

    Thread gameLoopThread;
    Thread placePipesThread;

    boolean gameOver = false;
    double score = 0;

    Panel() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    velocityY = -9;

                }

            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setBackground(Color.gray);
        setFocusable(true); // make sure that our panel class takes in the key events

        backgroundImg = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        gameLoopThread = new Thread(() -> {

            while (true) {
                move();
                // repaint();
                try {
                    Thread.sleep(1000 / 40);// Adjusted sleep duration for smoother animation (40 frames per second)
                } catch (InterruptedException e) {
                    System.out.println("Game loop thread interrupted.");
        
                }
            }

        });

        placePipesThread = new Thread(() -> {

            while (true) {
                pipesPlaced();
                try {
                    Thread.sleep(1500); // Adjusted sleep duration for placing pipes every second
                } catch (InterruptedException e) {
                    System.out.println("Place pipes thread interrupted.");
                }
            }
        });
        gameLoopThread.start();
        placePipesThread.start();

    }

    public void move() {

        // Check if game over
        if (gameOver) {
            gameLoopThread.interrupt();
            placePipesThread.interrupt();

            return;
        }
        // bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0); // the bird will stop after the screen is finished

        // pipes
        // bird.x += velocityX;
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (collision(bird, pipe)) {
                gameOver = true;
                return; // no need to continue checking for collision if game over
            }

            if(!pipe.passed && bird.x > pipe.x + pipe.width){ //if the bird passes the rigt side of the pipe, pipe.x starts on the left side and pipe.width gives us the right side of the pipe 
                   pipe.passed = true;
                   score += 0.5; // since there are 2 pipes, to count it as 1 i have splitted into half
                   System.out.println("Score:" + score);
            }
           
            
       

        }

        if (bird.y > boardHeight) { // if bird goes down
            gameOver = true;
        }

        // Calculate the position of the right edge of the background
        int backgroundRightEdge = 654; // x-coordinate of background + width

        // Remove pipes that have moved beyond the right edge of the background
        // pipes.removeIf(pipe -> pipe.x + pipe.width < backgroundRightEdge);

    }

    public boolean collision(Bird a, Pipe b) {
        Rectangle birdRect = new Rectangle(a.x, a.y, a.width, a.height);
        Rectangle pipeRect = new Rectangle(b.x, b.y, b.width, b.height);
        return birdRect.intersects(pipeRect);
    }

    public void pipesPlaced() {

        if (gameOver) {
            return;
        }

        // (0-1)*heightPipe/2 -> (0-256)
        // 128
        // 0-128-(0-256) --> 1/4 heightPipe -> 3/4 heightPipe

        int randomPipeY = (int) (yPipe - heightPipe / 4 - Math.random() * (heightPipe / 2));
        int openingSpace = boardHeight / 4; // we need some space for the opening so that bird can fly through

        // Top pipe
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        // Bottom pipe
        Pipe bottomPipe = new Pipe(bottomPipeImg);

        // since x y start at the top left corner
        // top pipe start + bottom of the top pipe+ opening space for bird
        bottomPipe.y = topPipe.y + heightPipe + openingSpace; // by default the pipe y is zero so i.e top of the screen,
                                                              // we nned to shift it down
        pipes.add(bottomPipe);



    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // background
        g.drawImage(backgroundImg, 600, 1, 390, 850, null);

        // bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        g.setColor(Color.RED); // Set color to red for the bounding box
        g.drawRect(bird.x, bird.y, bird.width, bird.height); // Draw bounding box
        




        // pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

            // Draw bounding box around pipe
            g.setColor(Color.RED); // Set color to red for the bounding box
            g.drawRect(pipe.x, pipe.y, pipe.width, pipe.height); // Draw bounding box
            
        }

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if(gameOver){
            g.drawString("Game Over: " + String.valueOf((int)score), 10, 35);
        }
        else{
            g.drawString("Score: "+ String.valueOf((int) score), 10, 35);
        }
    }

}

// Gravity is a change in velocity over time
