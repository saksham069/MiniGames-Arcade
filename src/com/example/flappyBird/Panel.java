package com.example.flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //to store all the pipes
import java.util.Random; //to place pipes at random positions
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Panel extends JPanel {

    private boolean jumping = false;
    private int birdX = 100;
    private int birdY = 400;
    private int velocityY = 0;

    // Images : these 4 variables will store our image objects
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int boardWidth = 390;
    int boardHeight = 850;

    int birdWidth = 34;
    int birdHeight = 24;

    Panel() {

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    jump();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        Thread jumpthread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10); // to generate a delay b/w each iteration
                } catch (InterruptedException e) {
                    e.printStackTrace(); // print exception, class name, method and line no.
                }

                // if jumping and not at max height, move upwards
                if (jumping && birdY >= 400) {
                    velocityY = 30; // bird fly above
                }
                birdY -= velocityY;
                velocityY -= 1;           // gravity, dheere dheere neeche
                if (birdY >= 400) {       // max height attain?
                    birdY = 400;          // dont go above this much
                    velocityY = 0;        // stop moving upward
                    jumping = false;      // end jumping
                }
                repaint();

            }
        });
        jumpthread.start();

        setBackground(Color.gray);

        // load images(ye sab baad ka kaam)
        backgroundImg = new ImageIcon(getClass().getResource("/com/example/flappyBird/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/com/example/flappyBird/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/com/example/flappyBird/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/com/example/flappyBird/bottompipe.png")).getImage();
    }

    public void jump() {
        jumping = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(birdX, birdY, 80, 80);
        g.drawImage(backgroundImg, 600, 1, 390, 850, null);

    }
}
