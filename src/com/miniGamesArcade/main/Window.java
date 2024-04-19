package com.miniGamesArcade.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Class representing the game window
public class Window {

    private JFrame jFrame;
    private JPanel panel;

    // Constructor to initialize the window with a JPanel
    public Window(JPanel jPanel) {
        this.panel = jPanel;
        jFrame = new JFrame();
        jFrame.setResizable(false);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(panel); // Add the JPanel which is clicked to the JFrame
        jFrame.setVisible(true);
    }

    // Method to set a new JPanel as the content of the window
    public void setPanel(JPanel jPanel) {
        // Remove all components from the content pane
        jFrame.getContentPane().removeAll();
        // Remove the previous panel
        jFrame.remove(this.panel);

        // Assign the new panel
        this.panel = jPanel;
        // Add the new panel to the content pane
        jFrame.getContentPane().add(this.panel);

        // Revalidate the content pane to reflect changes
        jFrame.revalidate();
        // Repaint the window to update the display
        jFrame.repaint();
    }
}