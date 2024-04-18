package com.miniGamesArcade.games.brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Ball {
    
    // ball's position , dimeension and velocity variables
    private double x, y, dx, dy;
    private int ballSize = 30;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // contructor to initialise the ball
    public Ball() {
        x = 3 * screenSize.getHeight() / 4;
        y = 3 * screenSize.getHeight() / 4;
        dx = 1;
        dy = -3;

    }

    // to draw ball
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, ballSize, ballSize);
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int) x, (int) y, ballSize, ballSize);
    }

    // to set ball position
    public void update() {
        x += dx;
        y += dy;

        // left wall
        if (x < 0) {
            dx = -dx;
        }

        // bottom wall
        if (y < 0) {
            dy = -dy;
        }

        // right wall
        if (x > screenSize.getWidth() - ballSize) {
            dx = -dx;
        }

        // upper wall
        if (y > screenSize.getHeight() - ballSize) {
            dy = -dy;
        }
    }

    // to top ball
    public void stopBall() {
        dx = 0;
        dy = 0;
    }

    // collidor for ball
    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, ballSize, ballSize);
    }

    // setters and getters for x , y , Vx , Vy of ball
    public void setDy(double changedDY) {
        dy = changedDY;
    }

    public void setDx(double changedDx) {
        dy = changedDx;
    }

    public double getDy() {
        return dy;
    }

    public double getDx() {
        return dx;
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

}
