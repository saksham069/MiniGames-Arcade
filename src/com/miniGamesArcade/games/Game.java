package com.miniGamesArcade.games;

import javax.swing.JPanel;
import java.awt.Image;
import com.miniGamesArcade.main.Main;

//Abstract class representing a game in the mini games arcade.
public abstract class Game implements Runnable {

    // The panel where the game is displayed
    protected JPanel panel;

     // Thread for rendering the game
    protected Thread renderThread;

    // Frames per second for rendering
    static final int FPS = 120;
    protected Image icon;

    //method to start the game ( defined for each game)
    public void play() {
        Main.window.setPanel(panel);
        panel.requestFocus();
        renderThread.start();
    };

    //Gets the icon representing the game.
    public Image getIcon() {
        return this.icon;
    }

    //Main game loop for rendering the game.
    @Override
    public void run() {
        var timePerFrame = 1_000_000_000.0 / FPS;
        long lastFrame = System.nanoTime();
        long now;
        do {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                panel.repaint();
                lastFrame = now;
            }
        } while (true);
        // Infinite loop for continuous rendering
    }
}
