package com.example.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
    private JFrame jFrame;
    private JPanel panel;

    public Window(JPanel jPanel) {
        this.panel = jPanel;
        jFrame = new JFrame();
        jFrame.setResizable(false);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(panel);
        jFrame.setVisible(true);
    }

    public void setPanel(JPanel jPanel) {
        jFrame.remove(this.panel);
        this.panel = jPanel;
        jFrame.add(this.panel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}
