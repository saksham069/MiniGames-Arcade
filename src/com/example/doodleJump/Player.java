package com.example.doodleJump;

import java.awt.Rectangle;

class Player {
    Rectangle collider;

    Player(int x, int y, int w, int h) {
        collider = new Rectangle(x, y, w, h);
    }
}
