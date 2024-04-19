package com.miniGamesArcade.games.snake;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.miniGamesArcade.pauseMenu.MenuOverlay;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Panel class representing the game panel where the Snake game is played
public class Panel extends JPanel {

    // Declaring objects
    private SnakeObj snake;
    private Food food;
    private Score score;

    // Declaring colliders
    private Rectangle foodRect;
    private Rectangle snakeHeadRect;

    // Declaring variables
    private int speed;
    private boolean running = true;
    private int directionController;

    // Getting screen size (using final to prevent accidental change)
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Declaring variables for pause menu
    private final boolean[] paused;
    private final JFrame parentFrame;
    private MenuOverlay overlay;

    // Declaring the game thread
    private Thread gameThread;

    // Constructor
    public Panel() {
        this.setBackground(Color.BLACK); // Set background color

        // PAUSE MENU
        paused = new boolean[] { false }; 
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); 
        overlay = new MenuOverlay(parentFrame, new Snake(), paused); 

        // Create game objects
        snake = new SnakeObj();
        food = new Food();
        score = new Score();

        // Create game thread and start it
        gameThread = new Thread(this::run);
        gameThread.start();

        // Initialize variables
        directionController = 1;
        speed = 100;

        // Implement keyListener using anonymous class
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            // Control the direction of the snake using key presses
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (directionController != 2) {
                            directionController = 1;
                        }
                        break;

                    case KeyEvent.VK_LEFT:
                        if (directionController != 1) {
                            directionController = 2;
                        }
                        break;

                    case KeyEvent.VK_UP:
                        if (directionController != 4) {
                            directionController = 3;
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        if (directionController != 3) {
                            directionController = 4;
                        }
                        break;

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

    // Game loop
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

            // Update snake position
            snake.update(directionController);
            
            // Check for collisions
            collisionChecker(); 
        }
    }

    // Render method to draw game objects
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //draw objects
        snake.draw(g2d); 
        food.draw(g2d); 
        score.draw(g2d); 

        // Render "Game Over" if game is over
        if (!running) {
            g.setFont(new Font("Courier New", Font.BOLD, 70));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", (int) screenSize.getWidth() / 2 - 180, (int) screenSize.getHeight() / 2);
        }
    }

    // Collision checking method
    public void collisionChecker() {
        snakeHeadRect = snake.getRectHead(); 
        foodRect = food.getRect(); 

        if (snakeHeadRect.intersects(foodRect)) { 
            snake.setBodyParts(); 
            food.foodReSpawn(); 
            score.addScore(20); 
            adjustSpeed(); 
        }

        // Check for collisions with snake's own body
        running = snake.checkBodyCollisions(); 

        // Check for collisions with screen borders
        snake.checkBorderCollisions(); 
    }

    // Method to adjust game speed
    public void adjustSpeed() {
        if (speed != 20) { 
            speed -= 3; 
        }
    }
}