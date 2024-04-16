package com.example.brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Ball {

    private double x, y, dx, dy;
    private int ballSize = 30;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Ball() {
        // initialising the ball
        x = 3 * screenSize.getHeight() / 4;
        y = 3*screenSize.getHeight()/4;
        dx = 1;
        dy = -3;

    }

    public void update() {
        setPos();
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int) x, (int) y, ballSize, ballSize);
    }

    public void setPos() {
        // will ask if the ball hit the wall and update accoridngly

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

    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, ballSize, ballSize);
    }

    public void setDy(double changedDY) {
        dy = changedDY;
    } // method can be made better , check if it can be done using just properties

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

    public void stopBall(){
        dx=0;
        dy=0;
    }

}
