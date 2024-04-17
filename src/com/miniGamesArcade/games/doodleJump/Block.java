package com.miniGamesArcade.games.doodleJump;

import java.awt.geom.RoundRectangle2D;

class Block {
    final RoundRectangle2D collider;
    double setY;
    private final static int blockWidth = 100;
    private static int blockCount;

    Block(double x, double y) {
        collider = new RoundRectangle2D.Double(x, y, blockWidth, 10, 20, 20);
        setY = y;
        blockCount++;
    }

    static int getWidth(){
        return blockWidth;
    }

    static void initCount(){
        blockCount = 0;
    }

    static int getCount(){
        return blockCount;
    }


}
