package com.miniGamesArcade.games.flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //to store all the pipes
import java.util.Random;
import javax.swing.*;

class Panel extends JPanel {

    
    private int velocityX = -4; // moves pipes to the left speed
    // private int velocityY = 0;
    private final int gravity = 1;

    // Images : these 4 variables will store our image objects
    Image backgroundImg;
    
    Image topPipeImg;
    Image bottomPipeImg;

    int boardWidth = 1600;
    int boardHeight = 850;

  
    Bird bird;
    

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
                    bird.changeBirdVelocity(); 

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
        
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        bird = new Bird();
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
                    Thread.sleep(1500); // Adjusted sleep duration for placing pipes every 1.5 second
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
        bird.birdMove();
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (collision( pipe)) {
                gameOver = true;
                return; // no need to continue checking for collision if game over
            }

            if(!pipe.passed && bird.getBirdX() > pipe.x + pipe.width){ //if the bird passes the rigt side of the pipe, pipe.x starts on the left side and pipe.width gives us the right side of the pipe 
                   pipe.passed = true;
                   score += 0.5; // since there are 2 pipes, to count it as 1 i have splitted into half
                   System.out.println("Score:" + score);
            }
           
            
       

        }

        //temp sol 
        if (bird.getBirdY() > boardHeight) { // if bird goes down
            gameOver = true;
        }

        

    }

    public boolean collision(Pipe b) {
        Rectangle birdRect = new Rectangle(bird.getBirdX(), bird.getBirdY(), bird.getBirdWidth(), bird.getBirdHeight());
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
        Graphics2D g2d = (Graphics2D) g;

        // background
        g2d.drawImage(backgroundImg, 1, 1, 1600, 850, null);

        // bird
       

        bird.draw(g2d);


        // pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            
            g2d.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

            // Draw bounding box around pipe
            g2d.setColor(Color.RED); // Set color to red for the bounding box
            g2d.drawRect(pipe.x, pipe.y, pipe.width, pipe.height); // Draw bounding box
            
        }

        //score
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 32));
        if(gameOver){
            g2d.drawString("Game Over: " + String.valueOf((int)score), 10, 35);
        }
        else{
            g2d.drawString("Score: "+ String.valueOf((int) score), 10, 35);
        }
    }

}

// Gravity is a change in velocity over time
