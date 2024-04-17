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

public class Panel extends JPanel {

    Blocks block;
    // private boolean gameStarted;
    private JLabel startLabel;

    private final boolean[] paused;
    private final JFrame parentFrame;
    private MenuOverlay overlay;

    Panel() {
        // PAUSE MENU
        paused = new boolean[] { false };
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        overlay = new MenuOverlay(parentFrame, new SimonGame(), paused);

        block = new Blocks();
        // gameStarted = false;

        // Initialize the start label
        startLabel = new JLabel("Press any key to start");
        startLabel.setFont(new Font("Arial", Font.BOLD, 20));
        startLabel.setForeground(Color.RED);
        startLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(startLabel); // Add the label to the panel

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!block.getgameStarted()) { // Start the game only if it hasn't started yet
                    block.start(); // Start the game
                    block.setgameStarted(true); // Update gameStarted flag
                    remove(startLabel);

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX(), y = e.getY();

                if (!block.getcreatingPattern() && !block.getGameOver()) {

                    if (x > block.getstartX() && x < block.getstartX() + block.getblockWidth() && y > block.getstartY()
                            && y < block.getstartY() + block.getblockHeight()) {
                        block.setFlashed(1);
                        block.setTicks(1);
                    }

                    else if (x > block.getstartX() + block.getblockWidth()
                            && x < block.getstartX() + block.getblockWidth() * 2 && y > block.getstartY()
                            && y < block.getstartY() + block.getblockHeight()) {
                        block.setFlashed(2);
                        block.setTicks(1);
                    }

                    else if (x > block.getstartX() && x < block.getstartX() + block.getblockWidth()
                            && y > block.getstartY() + block.getblockHeight()
                            && y < block.getstartY() + block.getblockHeight() * 2) {
                        block.setFlashed(3);
                        block.setTicks(1);
                    }

                    else if (x > block.getstartX() + block.getblockWidth()
                            && x < block.getstartX() + block.getblockWidth() * 2
                            && y > block.getstartY() + block.getblockHeight()
                            && y < block.getstartY() + block.getblockHeight() * 2) {
                        block.setFlashed(4);
                        block.setTicks(1);
                    }

                    if (block.getFlashed() != 0) {
                        if (block.getPattern().get(block.getIndexPattern()) == block.getFlashed()) {
                            block.setIndexPattern(block.getIndexPattern() + 1);

                        } else {
                            block.start();
                            block.setGameOver(true);
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

        block.start();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (block != null) {
            block.draw((Graphics2D) g);

            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Arial", Font.BOLD, 35));
            g.drawString("Level:" + block.getLevel(), 20, 40);
        }

    }

}
