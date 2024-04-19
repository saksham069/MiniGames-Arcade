package com.miniGamesArcade.games.doodleJump;

import java.awt.geom.RoundRectangle2D;

//Represents a block in the Doodle Jump game.
class Block {

    // variables for collider area and other properties of the block
    final RoundRectangle2D collider;
    double setY;
    private final static int blockWidth = 100;
    private static int blockCount;

    // constructor for block
    Block(double x, double y) {
        collider = new RoundRectangle2D.Double(x, y, blockWidth, 10, 20, 20);
        setY = y;
        blockCount++;
    }

    // Gets the width of the block.
    static int getWidth() {
        return blockWidth;
    }

    // Initializes the block count.
    static void initCount() {
        blockCount = 0;
    }

    // Gets the number of blocks created.
    static int getCount() {
        return blockCount;
    }

}
