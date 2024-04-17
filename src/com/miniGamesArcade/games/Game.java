package com.miniGamesArcade.games;

import javax.swing.JPanel;

import com.miniGamesArcade.main.Main;

public abstract class Game implements Runnable {
    protected JPanel panel;
    protected Thread renderThread;
    static final int FPS = 120;

    public void play() { // panel recreate hota hai ya ni pata ni abhi bhi
        Main.window.setPanel(panel);
        panel.requestFocus();
        renderThread.start();
    };

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
