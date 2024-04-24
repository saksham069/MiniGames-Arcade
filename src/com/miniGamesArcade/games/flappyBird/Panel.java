package com.miniGamesArcade.games.flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.*;
import java.util.List;

import com.miniGamesArcade.pauseMenu.MenuOverlay;

class Panel extends JPanel {

    // velocity of pipes
    private int velocityX = -4;

    // for pause menu
    private final boolean[] paused;
    private final JFrame parentFrame;
    private MenuOverlay overlay;

    // Images for background, top pipe, and bottom pipe
    private Image backgroundImg;
    private Image topPipeImg;
    private Image bottomPipeImg;

    // game board dimensions
    private int boardHeight;

    // Bird class object
    Bird bird;
    Random random;

    // thread for gameloop and placing pipes
    Thread gameLoopThread;
    Thread placePipesThread;

    private boolean gameOver;
    private double score;

    // list to store pipes
    static ArrayList<Pipe> pipes;

    // JLabel to display start message
    private JLabel startJLabel;
    final Object pipesLock;

    private boolean spacePressed;

    Panel() {

        // initialize board dimensions
        boardHeight = 850;

        // game state variables
        gameOver = false;
        score = 0;
        random = new Random();
        setBackground(Color.gray);
        setFocusable(true);

        // PAUSE MENU
        paused = new boolean[] { false };
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        overlay = new MenuOverlay(parentFrame, new FlappyBird(), paused);

        // load images for background and pipes
        backgroundImg = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        pipesLock = new Object();
        // initialize bird and pipes
        bird = new Bird();
        pipes = new ArrayList<>();

        spacePressed = false;

        addKeyListener(new KeyListener() {
            

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    paused[0] = !paused[0];
                    overlay.setVisible(paused[0]);
                }
                startGame();

                if (e.getKeyCode() == KeyEvent.VK_SPACE && !spacePressed) {
                    bird.changeBirdVelocity();
                    spacePressed = true;

                }

            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = false;
                }
            }
        });

        // initialize threads
        initializeThreads();

        // Add start label
        startJLabel = new JLabel("Press any key to start the game");
        startJLabel.setForeground(Color.white);
        startJLabel.setFont(new Font("Arial", Font.BOLD, 20));
        startJLabel.setBounds(500, 400, 300, 30);
        add(startJLabel);

    }

    // Initialize game loop and pipe placement threads
    private void initializeThreads() {
        gameLoopThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    // System.out.println("Game loop thread interrupted.");
                }

                if (paused[0])
                    continue;
                move();
            }
        });

        placePipesThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // System.out.println("Place pipes thread interrupted.");
                }
                if (paused[0])
                    continue;
                pipesPlaced();
            }
        });

    }

    // start the game
    private void startGame() {

        if (startJLabel != null) {
            remove(startJLabel);
            startJLabel = null;

        }

        // start game loop and place pipe thread if not already running
        if (gameLoopThread != null && !gameLoopThread.isAlive()) {
            gameLoopThread.start();
        }

        if (placePipesThread != null && !placePipesThread.isAlive()) {
            placePipesThread.start();
        }

    }

    // move objects in game (bird and pipes)
    public void move() {

        // check if game is over
        if (gameOver) {
            gameLoopThread.interrupt();
            placePipesThread.interrupt();

            return;
        }

        // move the bird
        bird.birdMove();

        // move each pipe
        synchronized (pipesLock) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.setxPipe(pipe.getxPipe() + velocityX);

                // check for collision with the bird
                if (collision()) {
                    gameOver = true;
                    return;
                }

                // Check if the bird has passed the pipe
                if (!pipe.isPassed() && bird.getBirdX() > pipe.getxPipe() + pipe.getwidthPipe()) {
                    pipe.setPassed(true);
                    score += 0.5;
                    System.out.println("Score:" + score);
                }
            }
        }

        // Check if the bird has fallen out of the screen
        if (bird.getBirdY() > boardHeight) {
            gameOver = true;
        }
    }

    // check for collision between bird and pipe
    public boolean collision() {
        Rectangle birdRect = new Rectangle(bird.getBirdX(), bird.getBirdY(), bird.getBirdWidth(), bird.getBirdHeight());
        synchronized (pipesLock) {
            for (Pipe pipe : pipes) {
                Rectangle pipeRect = new Rectangle(pipe.getxPipe(), pipe.getyPipe(), pipe.getwidthPipe(),
                        pipe.getheightPipe());
                if (birdRect.intersects(pipeRect)) {
                    return true;
                }
            }
        }
        return false;
    }

    // place pipes in the game
    public void pipesPlaced() {

        // check if game is over
        if (gameOver) {
            return;
        }

        Pipe pipe = new Pipe(topPipeImg, bottomPipeImg);
        pipe.pipesPlaced();
    }

    // paint components
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // draw background image
        g2d.drawImage(backgroundImg, 1, 1, 1600, 850, null);

        // Draw bird
        bird.draw(g2d);

        // Draw each pipe

        List<Pipe> pipesCopy;   // synchronization ensures that only one thread at
        // a time can access or modify
        // the pipes list, like pipePlaced placing the pipes and awt thread is
        // drawing the pipes
        synchronized (pipesLock) {
            pipesCopy = new ArrayList<>(pipes); // Create a copy of the pipes list
        }
        for (Pipe pipe : pipesCopy) {
            pipe.draw(g2d);
        }

       

        // Draw score or game over message
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g2d.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        } else {
            g2d.drawString("Score: " + String.valueOf((int) score), 10, 35);
    }
    }
}
