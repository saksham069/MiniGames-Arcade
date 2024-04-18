package com.miniGamesArcade.main;

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

        jFrame.getContentPane().removeAll();
        jFrame.remove(this.panel);
        this.panel = jPanel;
        jFrame.getContentPane().add(this.panel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}
