package com.example.brickBreaker;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Panel extends JPanel {
    private boolean running;

    // entities
    Ball ball;
    Paddle paddle;
    Map map;

    // fields
    Rectangle ballRect;
    Rectangle paddleRect;

    // inputs
    private MouseInputs mouseInputs;

    // constructor
    public Panel() {

        // initiallsing the mouse inputs
        mouseInputs = new MouseInputs();
        addMouseMotionListener(mouseInputs);

        // initialising the loop and the entities
        running = true;
        ball = new Ball();
        paddle = new Paddle();
        map = new Map(5, 10);

        // different thread for the game loop
        // This loop runs continuously, and since it's executed on the Swing event
        // dispatch thread (EDT), it blocks the EDT, preventing Swing from handling user
        // input or updating the UI.

        // To fix this freezing issue, you should avoid running long-running tasks on
        // the EDT. Instead, you can use a separate thread to run the game loop.
        Thread gameThread = new Thread(this::run);
        gameThread.start();

        // IMPLEEMNT KEYBOARD LATER ON
        // // listens to the key pressed (for keyboards)
        // addKeyListener(new KeyboardInputs(this));
        // // using this to pass an object of gamepanel inside keyborard inputs
    }

    public void run() {

        while (running) {
            // update
            update();

            // render or draw
            // draw();

            // display
            try {
                // too fast
                Thread.sleep(4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        collisionChecker();
        ball.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // super is used to access the superclass methods
        // basically says ki hey do all the things that you need to do in that class and
        // then i am gonna start painting myself
        Graphics2D g2d = (Graphics2D) g;

        ball.draw(g2d);
        paddle.draw(g2d);
        map.draw(g2d);
    }

    public void collisionChecker() {
        ballRect = ball.getRect();
        paddleRect = paddle.getRect();

        if (ballRect.intersects(paddleRect)) {
            ball.setDy(-ball.getDy());
        }

        A: for (int row = 0; row < map.getMapArray().length; row++) {
            for (int col = 0; col < map.getMapArray()[0].length; col++) {

                if (map.getMapArray()[row][col] > 0) {
                    int brickx = col * (int) map.getBrickWidth() + map.paddingX;
                    int bricky = row * (int) map.getBrickHeight() + map.paddingY;
                    int brickHeight = (int) map.getBrickHeight();
                    int brickWidth = (int) map.getBrickWidth();

                    // can make this method in map only , it will help save a lot of methods TRY IT
                    Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);

                    if (ballRect.intersects(brickRect)) {
                        map.setBrick(row, col, 0);
                        ball.setDy(-ball.getDy());

                        break A;
                    }
                }

            }
        }
    }

    // mouse inputs
    private class MouseInputs implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            paddle.setPaddlePos(e.getX());
        }
    }

}

// there's this built in feature in java where it can tell if one rectangle
// intersects with another rectangle
