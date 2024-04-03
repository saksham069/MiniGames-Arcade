package com.example.pauseMenu;

import javax.swing.JWindow;
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

    public MenuOverlay(JFrame parent, ArrayList<FuncInt> tasks) { // add exactly 3 tasks issmei warna, would throw error
        super(parent);
        BG_COLOR = new Color(0, 0, 0, 100);
        WIDTH = 800;
        HEIGHT = 600;
        OPTIONS = new ArrayList<Option>();
        OPTIONS.add(new Option(WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, tasks.get(0)));
        OPTIONS.add(new Option(2 * WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, tasks.get(1)));
        OPTIONS.add(new Option(3 * WIDTH / 4 - 50, HEIGHT / 2 - 50, 100, 100, tasks.get(2)));

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