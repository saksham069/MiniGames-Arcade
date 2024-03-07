package com.example.menu;

import javax.swing.JPanel;

import com.example.doodleJump.DoodleJump;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class Panel extends JPanel {
    private final Dimension screenSize; // could make public in an outer class
    private final double screenHeight;
    private final double screenWidth;
    private ArrayList<GameItem> games;

    Panel() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.getHeight();
        screenWidth = screenSize.getWidth();

        games = new ArrayList<GameItem>();
        games.add(new GameItem((int) screenWidth / 4 - 100, (int) screenHeight / 2 - 100,
                new DoodleJump()));
        // games.add(new GameItem(2 * (int) screenWidth / 4 - 100, (int) screenHeight /
        //         2 -
        //         100,
        //         new Game2()));
        // games.add(new GameItem(3 * (int) screenWidth / 4 - 100, (int) screenHeight /
        //         2 -
        //         100,
        //         new Game3()));

        // replace Game2/3 with GameClass and "shift + alt + f"

        this.setBackground(Color.BLACK);
        this.setForeground(Color.GRAY);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                games.forEach((g) -> {
                    if (g.getRect().contains(e.getPoint())) {
                        g.start();
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

    public void paintComponent(Graphics g) { // add graphics
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        games.forEach((gm) -> {
            g2d.fill(gm.getRect());
        });
        repaint();
    }
}
