package com.miniGamesArcade.games.flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.*;

import com.miniGamesArcade.pauseMenu.MenuOverlay;

class Panel extends JPanel {

    private int velocityX = -4;
    private final int gravity = 1;

    private final boolean[] paused;
    private final JFrame parentFrame;
    private MenuOverlay overlay;

    private Image backgroundImg;
    private Image topPipeImg;
    private Image bottomPipeImg;

    private int boardWidth;
    private int boardHeight;

    Bird bird;
    Random random;

    Thread gameLoopThread;
    Thread placePipesThread;

    private boolean gameOver;
    private double score; 

    static ArrayList<Pipe> pipes = new ArrayList<>();

    private JLabel startJLabel;

    Panel() {
        boardWidth = 1600;
        boardHeight = 850;
        gameOver = false;

        score = 0;
        random = new Random();
        setBackground(Color.gray);
        setFocusable(true);
        
       
   
        // PAUSE MENU
        paused = new boolean[] { false };
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        overlay = new MenuOverlay(parentFrame, new FlappyBird(), paused);

        backgroundImg = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();

        bird = new Bird();

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    paused[0] = !paused[0];
                        overlay.setVisible(paused[0]);
                }
                startGame();

                

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
        initializeThreads();

        // Add start label
        startJLabel = new JLabel("Press any key to start the game");
        startJLabel.setForeground(Color.white);
        startJLabel.setFont(new Font("Arial", Font.BOLD, 20));
        startJLabel.setBounds(500, 400, 300, 30);
        add(startJLabel);

    }


    private void initializeThreads() {
        gameLoopThread = new Thread(() -> {
            while (true) {
                move();
                try {
                    Thread.sleep(1000 / 40);
                } catch (InterruptedException e) {
                    // System.out.println("Game loop thread interrupted.");
                }
            }
        });

        placePipesThread = new Thread(() -> {
            while (true) {
                pipesPlaced();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // System.out.println("Place pipes thread interrupted.");
                }
            }
        });

    }

    private void startGame() {

        if (startJLabel != null) {
            remove(startJLabel);
            startJLabel = null;
        
        }
        if (gameLoopThread != null && !gameLoopThread.isAlive()) {
            gameLoopThread.start();
        }

        if (placePipesThread != null && !placePipesThread.isAlive()) {
            placePipesThread.start();
        }

    }

    public void move() {
        if (gameOver) {
            gameLoopThread.interrupt();
            placePipesThread.interrupt();
            return;
        }

        bird.birdMove();
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setX(pipe.getX() + velocityX);

            if (collision(pipe)) {
                gameOver = true;
                return;
            }

            if (!pipe.isPassed() && bird.getBirdX() > pipe.getX() + pipe.getWidth()) {
                pipe.setPassed(true);
                score += 0.5;
                System.out.println("Score:" + score);
            }
        }

        if (bird.getBirdY() > boardHeight) {
            gameOver = true;
        }
    }

    public boolean collision(Pipe b) {
        Rectangle birdRect = new Rectangle(bird.getBirdX(), bird.getBirdY(), bird.getBirdWidth(), bird.getBirdHeight());
        Rectangle pipeRect = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        return birdRect.intersects(pipeRect);
    }

    public void pipesPlaced() {
        if (gameOver) {
            return;
        }
        

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.pipesPlaced(boardWidth, 0, 64, 512, boardHeight, topPipeImg, bottomPipeImg);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(backgroundImg, 1, 1, 1600, 850, null);
        bird.draw(g2d);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g2d.drawImage(pipe.getImg(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
           // g2d.setColor(Color.white);
            //g2d.drawRect(pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight());
        }

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g2d.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        } else {
            g2d.drawString("Score: " + String.valueOf((int) score), 10, 35);
        }
    }
}
