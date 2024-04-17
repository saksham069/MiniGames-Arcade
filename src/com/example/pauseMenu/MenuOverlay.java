package com.example.pauseMenu;

import javax.swing.JWindow;

import com.example.main.Game;
import com.example.menu.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MenuOverlay extends JWindow {
    final Color BG_COLOR;
    final int WIDTH;
    final int HEIGHT;
    final ArrayList<Option> OPTIONS;
    final FuncInt RESUME_GAME_TASK;
    final FuncInt RESTART_GAME_TASK;
    final FuncInt HOME_BUTTON_TASK;

    public MenuOverlay(JFrame parent, Game game, boolean[] flipPaused) { // add exactly 2 tasks issmei
                                                                                     // warna, would
        // throw error
        super(parent);
        BG_COLOR = new Color(0, 0, 0, 100);
        WIDTH = 800;
        HEIGHT = 600;
        RESUME_GAME_TASK = () -> {
            flipPaused[0] = !flipPaused[0];
            this.dispose();
        };
        RESTART_GAME_TASK = () -> {
            game.play();
            this.dispose();
        };
        HOME_BUTTON_TASK = () -> {
            new Menu();
            this.dispose();
        };
        OPTIONS = new ArrayList<Option>();
        OPTIONS.add(new Option(WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, RESUME_GAME_TASK));
        OPTIONS.add(new Option(2 * WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, RESTART_GAME_TASK));
        OPTIONS.add(new Option(3 * WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, HOME_BUTTON_TASK));

        JPanel contentPane = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.RED);
                OPTIONS.forEach((o) -> {
                    g2d.fill(o.getSelectionArea());
                });

                g2d.dispose();
            }
        };
        contentPane.setBackground(BG_COLOR);
        contentPane.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                OPTIONS.forEach((o) -> {
                    if (o.getSelectionArea().contains(e.getPoint())) {
                        o.performTask();
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
        setContentPane(contentPane);

        contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();

        setLocationRelativeTo(parent);
    }
}