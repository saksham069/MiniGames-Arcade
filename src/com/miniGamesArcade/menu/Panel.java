package com.miniGamesArcade.menu;

import javax.swing.JPanel;

import com.miniGamesArcade.games.brickBreaker.BrickBreaker;
import com.miniGamesArcade.games.doodleJump.DoodleJump;
import com.miniGamesArcade.games.flappyBird.FlappyBird;
import com.miniGamesArcade.games.simonGame.SimonGame;
import com.miniGamesArcade.games.snake.Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

// Class representing the main menu panel
class Panel extends JPanel {

    // Dimensions of the screenSize
    private final Dimension screenSize;
    private final double screenHeight;
    private final double screenWidth;

    // List of game items displayed in the menu
    private ArrayList<GameItem> games;

    // Constructor to initialize the main menu panel
    Panel() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.getHeight();
        screenWidth = screenSize.getWidth();

        // Initialize the list of game items( all the games)
        games = new ArrayList<GameItem>();
        games.add(new GameItem((int) screenWidth / 4 - 100, (int) screenHeight / 3 - 100,
                new DoodleJump()));
        games.add(new GameItem(2 * (int) screenWidth / 4 - 100, (int) screenHeight /
                3 -
                100,
                new FlappyBird()));
        games.add(new GameItem(3 * (int) screenWidth / 4 - 100, (int) screenHeight /
                3 -
                100,
                new BrickBreaker()));
        games.add(new GameItem((int) screenWidth / 3 - 100, 2 * (int) screenHeight /
                3 -
                100,
                new Snake()));
        games.add(new GameItem(2 * (int) screenWidth / 3 - 100, 2 * (int) screenHeight /
                3 -
                100,
                new SimonGame()));

        //sets background and foreground color
        this.setBackground(Color.BLACK);
        this.setForeground(Color.GRAY);

        // Add mouse listener to handle mouse clicks on game items
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                games.forEach((g) -> {
                    if (g.getRect().contains(e.getPoint())) {
                        g.game.play();
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
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
    }

    // Method to paint the main menu panel
    public void paintComponent(Graphics g) { // add graphics
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw each game item on the panel
        games.forEach((gm) -> {
            g2d.drawImage(gm.game.getIcon(), gm.getRect().x, gm.getRect().y, gm.getRect().width, gm.getRect().height,
                    null);
        });
        repaint();
    }
}
