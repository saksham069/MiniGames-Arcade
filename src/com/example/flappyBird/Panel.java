package com.example.flappyBird;

import java.awt.*;
import java.awt.event.*;
// import java.sql.Time;
import java.util.ArrayList; //to store all the pipes
import java.util.Random;

// import java.util.Random; //to place pipes at random positions
import javax.swing.*;

class Panel extends JPanel implements ActionListener {

    // private boolean jumping = false;
    private int birdX = 650;
    private int birdY = 300;
    private int velocityX = -4; //moves pipes to the left speed 
    private int velocityY = 0;
    private int gravity = 1;
    // private boolean spaceKeyPressed = false; // Track if spacebar is pressed
    // private boolean jumpInProgress = false; // Track if a jump is currently in progress
    // private final int JUMP_STRENGTH = 30; // Adjust this as needed for jump strength
    // private final int JUMP_COOLDOWN = 300; // Cooldown period between jumps (milliseconds)
    // private long lastJumpTime = 0;
    // private final int MAX_JUMP_HEIGHT = 100; // Adjust as needed for maximum jump height

    // Images : these 4 variables will store our image objects
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int boardWidth = 920;
    int boardHeight = 850;

    int birdWidth = 34;
    int birdHeight = 24;

    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;

        Image img;
        Bird(Image img){
            this.img = img;
        }
    }

    //Pipes 
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
         

       Pipe(Image img){
        this.img = img;
       }
    }
    
    Bird bird;
    Timer gameLoop;
    Timer placePipesTimer;
    ArrayList<Pipe> pipes;
    Random random = new Random();

    Panel() {

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                //     spaceKeyPressed = true;
                // }
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    velocityY = -9;
                    
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                //     spaceKeyPressed = false;
                // }
            }

        });

        // Thread jumpthread = new Thread(() -> {
        //     while (true) {
        //         try {
        //             Thread.sleep(30); // to generate a delay b/w each iteration
        //         } catch (InterruptedException e) {
        //             e.printStackTrace(); // print exception, class name, method and line no.
        //         }

        //          // If spacebar is pressed and bird is in the air, flap
        //     if (spaceKeyPressed && birdY >= 0 ) {
        //         jump();
        //     }

            


        //         // if jumping and not at max height, move upwards
        //         if (jumping) {
        //             if (birdY <= MAX_JUMP_HEIGHT) { // Check if bird has reached max jump height
        //                 velocityY = -velocityY; // Reverse velocity to apply gravity
        //             }
        //             birdY -= velocityY;
        //              velocityY -= 2; // Apply gravity
        //             if (birdY >= 300) { // Check if bird has reached max height
        //                 birdY = 300; // Cap the bird's position
        //                 velocityY = 0; // Reset velocity
        //                 jumping = false; // Stop jumping
        //                 jumpInProgress = false; // Mark jump as complete
        //             }
        //             // repaint();
        //         }
                

        //     }
        // });
        // jumpthread.start();

        setBackground(Color.gray);
        setFocusable(true); // make sure that our panel class takes in the key events
        // addKeyListener((KeyListener) this);


        // load images(ye sab baad ka kaam)
        backgroundImg = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        //place pipes timer 
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                pipesPlaced();   // going to place a new pipe every 1.5sec
            }
        });
        placePipesTimer.start();
        //game timer
        gameLoop = new Timer(1000/60, this); //1000/60 = 16.6, 1000 is 1 sec
        gameLoop.start();
    }

    // public void jump() {
    //     // jumping = true;
    //     // if (!jumpInProgress) {
    //     //     jumping = true;
    //     //     jumpInProgress = true;
    //     //     velocityY = JUMP_STRENGTH; // Set initial velocity to jump strength
    //     //     lastJumpTime = System.currentTimeMillis(); // Record the time of the last jump
    //     // }
    //     if (!jumping) {
    //         jumping = true;
    //        velocityY = JUMP_STRENGTH;  // Set initial velocity to jump strength
    //     }
    // }

    public void move(){
        //bird
        velocityY+= gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0); // the bird will stop after the screen is finished

        //pipes
        birdX += velocityX;
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }

        // Calculate the position of the right edge of the background
           int backgroundRightEdge = 653; // x-coordinate of background + width

            // Remove pipes that have moved beyond the right edge of the background
          pipes.removeIf(pipe -> pipe.x + pipe.width < backgroundRightEdge);
       
    }

    public void pipesPlaced(){

        //(0-1)*heightPipe/2 -> (0-256)
        //128
        //0-128-(0-256) --> 1/4 heightPipe -> 3/4 heightPipe
        
        int randomPipeY = (int)(yPipe - heightPipe/4 - Math.random()*(heightPipe/2));
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        
         
        pipes.add(topPipe);
    }

    public void paintComponent(Graphics g) {
        // System.out.println("draw");
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        // g.fillOval(birdX, birdY, 80, 80);
         
        //background
        g.drawImage(backgroundImg, 600, 1, 390, 850, null);

        //bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null );

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
     
    }
}













// Gravity is a change in velocity over time