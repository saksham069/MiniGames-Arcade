package com.example.doodleJump;

import java.awt.geom.RoundRectangle2D;

class Block {
    RoundRectangle2D collider;

    Block(double x, double y) {
        collider = new RoundRectangle2D.Double(x, y, 100, 10, 20, 20);
    }
}
