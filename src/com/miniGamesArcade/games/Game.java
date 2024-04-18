package com.miniGamesArcade.games;

import javax.swing.JPanel;
import java.awt.Image;
import com.miniGamesArcade.main.Main;

public abstract class Game implements Runnable {
    protected JPanel panel;
    protected Thread renderThread;
    static final int FPS = 120;
    protected Image icon;

    public void play() {
        Main.window.setPanel(panel);
        panel.requestFocus();
        renderThread.start();
    };

    public Image getIcon() {
        return this.icon;
    }

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

    }
}
