package com.example.doodleJump;

import com.example.main.Game;
import com.example.main.Main;

public class DoodleJump implements Game {
    private Panel panel;
    private final Thread renderThread;
    private final int FPS = 120;

    public DoodleJump() {
        renderThread = new Thread(this);
    }

    @Override
    public void play() {
        panel = new Panel(); // check if restarting makes new panel or not
        Main.window.setPanel(panel);
        panel.requestFocus();
        renderThread.start();
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
