package com.example.doodleJump;

import java.awt.Rectangle;

class Player {
    Rectangle frame;
    Rectangle collider;

    Player(int x, int y, int w, int h) {
        frame = new Rectangle(x, y, w, h); // make constant yahi pe
        collider = new Rectangle(x, y + 9 * h / 10, w, h / 10); // ise bhi
    }

    void setPos(double x, double y, double w, double h) {
        frame.setFrame(x, y, w, h);
        collider.setFrame(x, y + 9 * h / 10, w, h / 10);
    }
}
