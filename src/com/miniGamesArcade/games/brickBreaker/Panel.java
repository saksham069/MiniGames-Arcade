package com.miniGamesArcade.games.brickBreaker;

import javax.swing.*;
import com.miniGamesArcade.pauseMenu.MenuOverlay;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Panel class representing the game panel where the BrickBreaker game is played
public class Panel extends JPanel {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    // Declaring objects
    private Ball ball;
    private Paddle paddle;
    private Map map;
    private Score score;
    private int temp = 0;

    // Declaring colliders
    private Rectangle ballRect;
    private Rectangle paddleRect;

    //speed variable and running to run the game loop 
    private int speed;
    private boolean running;

    // Declaring variables for pause menu
    private final boolean[] paused;
    private final JFrame parentFrame;
    private MenuOverlay overlay;

    // Declaring the game thread
    private Thread gameThread;

    // constructor
    public Panel() {
        this.setBackground(Color.BLACK);

        // PAUSE MENU
        paused = new boolean[] { false };
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        overlay = new MenuOverlay(parentFrame, new BrickBreaker(), paused);
        
        // Create game objects
        ball = new Ball();
        paddle = new Paddle();
        map = new Map(9, 13);
        score = new Score();

        // Create game thread and start it
        gameThread = new Thread(this::run);
        gameThread.start();

        // Initialize variables
        running = true;
        speed = 6;

        //setting paddle position based on x-cordinates using anonmous class
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
           
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                paddle.setPaddlePos(e.getX());
            }
        });

        // Implementing  keyListener using anonymous class
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        paused[0] = !paused[0]; // Toggle pause state
                        overlay.setVisible(paused[0]); // Show/hide pause menu overlay
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        

    }

    //Game Loop
    public void run() {
        while (running) {
            try {
                // Adjust game speed
                Thread.sleep(speed);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (paused[0])
                // Skip iteration if paused
                continue;

            // Update ball position
            ball.update();

            // Check for collisions
            collisionChecker(); 
        }
    }

    // Render method to draw game objects
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //draw objects
        map.draw(g2d);
        ball.draw(g2d);
        paddle.draw(g2d);
        score.draw(g2d);

        // Render "YAY YOU WIN" if game is winned
        if (map.isWin() == true) {
            g.setFont(new Font("Courier New", Font.BOLD, 50));
            g.setColor(Color.RED);
            g.drawString("YAY YOU WIN !", (int) screenSize.getWidth() / 2 - 180, (int) screenSize.getHeight() / 2);
            running = false;
        }

        // Render "Game Over" if game is over
        if (isLose() == true) {
            g.setFont(new Font("Courier New", Font.BOLD, 50));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", (int) screenSize.getWidth() / 2 - 180, (int) screenSize.getHeight() / 2);
            running = false;
        }

    }

    // Collision checking method
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

                    Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);

                    if (ballRect.intersects(brickRect)) {
                        map.hitBrick(row, col);
                        ball.setDy(-ball.getDy());

                        //if ball hit brick then add score and increase speed
                        score.addScore(20);
                        adjustSpeed();

                        //variable to make speed increase after an interval of 10 bricks
                        temp++;
                        break A;
                    }
                }

            }
        }
    }

    //Method to check if game over
    public boolean isLose() {
        boolean isLose = false;
        if (ball.gety() + 12 > paddle.gety()) {
            isLose = true;
        }
        return isLose;
    }

    //Method to gradually increase speed
    public void adjustSpeed() {
        if (speed != 2 && temp == 10) {
            speed -= 1;
            temp = 0;
        }
    }

   
   

}
