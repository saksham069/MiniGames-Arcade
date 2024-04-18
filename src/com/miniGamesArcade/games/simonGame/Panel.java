package com.miniGamesArcade.games.simonGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.miniGamesArcade.pauseMenu.MenuOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Panel class represents the graphical interface of the Simon game. It
 * handles user input
 * through keyboard and mouse events, displays game elements, and manages the
 * pause menu.
 */
public class Panel extends JPanel {

    private Blocks block; // Instance of the Blocks class representing game logic
    private JLabel startLabel; // Label displayed at the start of the game

    private final boolean[] paused; // Boolean array to track pause state
    private final JFrame parentFrame; // Reference to the parent JFrame
    private MenuOverlay overlay; // Overlay for the pause menu

    // for block flashing and timing
    private int ticks, dark;

    private int indexPattern;
    private Random random;
    private ArrayList<Integer> pattern;
    private boolean creatingPattern = true;

    private boolean gameStarted;

    private Thread thread;

    // Constructs a Panel object.

    Panel() {
        // PAUSE MENU
        paused = new boolean[] { false };
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        overlay = new MenuOverlay(parentFrame, new SimonGame(), paused);

        block = new Blocks(); // Initialize the game logic
        // random generator and pattern list
        random = new Random();

        // to store sequence of flashed blocks
        pattern = new ArrayList<Integer>();
        gameStarted = false;

        // to run block flashing logic
        thread = new Thread(this::run);
        thread.start();

        // Initialize the start label
        startLabel = new JLabel("Press any key to start");
        startLabel.setFont(new Font("Arial", Font.BOLD, 20));
        startLabel.setForeground(Color.RED);
        startLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(startLabel); // Add the label to the panel

        // Add key listener to handle keyboard events
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Toggle pause menu visibility on Escape key press
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    paused[0] = !paused[0];
                    overlay.setVisible(paused[0]);
                }

                // Start the game when any key is pressed
                if (!gameStarted) { // Start the game only if it hasn't started yet
                    start(); // Start the game
                    gameStarted = true; // Update gameStarted flag
                    remove(startLabel); // Remove the start label
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // Add mouse listener to handle mouse events
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Handle mouse clicks to interact with game elements
                int x = e.getX(), y = e.getY();

                if (!creatingPattern && !block.getGameOver()) {
                    // Check which block was clicked and set its state accordingly
                    if (x > block.getstartX() && x < block.getstartX() + block.getblockWidth() && y > block.getstartY()
                            && y < block.getstartY() + block.getblockHeight()) {
                        block.setFlashed(1);
                        ticks = 1;
                    } else if (x > block.getstartX() + block.getblockWidth()
                            && x < block.getstartX() + block.getblockWidth() * 2 && y > block.getstartY()
                            && y < block.getstartY() + block.getblockHeight()) {
                        block.setFlashed(2);
                        ticks = 1;
                    } else if (x > block.getstartX() && x < block.getstartX() + block.getblockWidth()
                            && y > block.getstartY() + block.getblockHeight()
                            && y < block.getstartY() + block.getblockHeight() * 2) {
                        block.setFlashed(3);
                        ticks = 1;
                    } else if (x > block.getstartX() + block.getblockWidth()
                            && x < block.getstartX() + block.getblockWidth() * 2
                            && y > block.getstartY() + block.getblockHeight()
                            && y < block.getstartY() + block.getblockHeight() * 2) {
                        block.setFlashed(4);
                        ticks = 1;
                    }

                    // Check if the clicked block matches the current pattern
                    if (block.getFlashed() != 0) {
                        if (pattern.get(indexPattern) == block.getFlashed()) {
                            // block.setIndexPattern(block.getIndexPattern() + 1);
                            indexPattern++; // Move to the next pattern element
                        } else {
                            start(); // Restart the game if the pattern is incorrect
                            block.setGameOver(true); // Set game over flag
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        start(); // Start the game logic

    }

    public void start() {

        // random generator and pattern list
        random = new Random();

        // to store sequence of flashed blocks
        pattern = new ArrayList<Integer>();

    }

    public void run() {
        while (!block.getGameOver()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (gameStarted) {
                ticks++; // keeps track of the number of iterations or time intervals that have passed
                         // since the game started
                

                // Reset flashed block and decrease darkness level every 20 ticks
                if (ticks % 20 == 0) {
                    block.setFlashed(0);
                    if (dark >= 0) {
                        dark--;
                    }
                }

                if (creatingPattern) {
                    if (dark <= 0) {
                        if (indexPattern >= pattern.size()) {
                            block.setFlashed(random.nextInt(40) % 4 + 1);
                            pattern.add(block.getFlashed());
                            indexPattern = 0;
                            creatingPattern = false;
                        } else {
                            block.setFlashed(pattern.get(indexPattern));
                            indexPattern++;
                        }
                        dark = 2;
                    }
                } else if (indexPattern == pattern.size()) {
                    creatingPattern = true;
                    indexPattern = 0;
                    dark = 2;
                    block.setFlash(true);
                    block.setLevel(block.getLevel() + 1);
                }
            }
        }
    }

    /**
     * Overrides the paintComponent method to draw game elements on the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (block != null) {
            block.draw((Graphics2D) g); // Draw game elements using the Blocks class
            // Display the current level
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Arial", Font.BOLD, 35));
            g.drawString("Level:" + block.getLevel(), 20, 40);
        }
    }
}
