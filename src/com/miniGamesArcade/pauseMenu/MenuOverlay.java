package com.miniGamesArcade.pauseMenu;

import javax.swing.JWindow;

import com.miniGamesArcade.games.Game;
import com.miniGamesArcade.menu.Menu;

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

    public MenuOverlay(JFrame parent, Game game, boolean[] paused) {
        super(parent);
        BG_COLOR = new Color(0, 0, 0, 100);
        WIDTH = 800;
        HEIGHT = 600;
        RESUME_GAME_TASK = () -> {
            paused[0] = false;
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
        OPTIONS.add(new Option(WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, RESUME_GAME_TASK,
                "res/play.png"));
        OPTIONS.add(new Option(2 * WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, RESTART_GAME_TASK,
                "res/restart.png"));
        OPTIONS.add(new Option(3 * WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, HOME_BUTTON_TASK,
                "res/home.png"));

        JPanel contentPane = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.black);
                OPTIONS.forEach((o) -> {
                    g2d.draw(o.getSelectionArea());
                    g2d.drawImage(o.getIcon(), o.getSelectionArea().x + 10, o.getSelectionArea().y + 10,
                            o.getSelectionArea().width - 20, o.getSelectionArea().height - 20, null);
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