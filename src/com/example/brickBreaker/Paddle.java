package com.example.brickBreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Paddle {

    private double x,y;
    private int paddleHeight=20, paddleWidth=100;
    private final Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();

    Paddle(){
        //initialising the paddle
        x=(screenSize.getWidth()-paddleWidth)/2;
        y=screenSize.getHeight()-100;

    }

    public void draw(Graphics2D g){
         // to change color
         g.setColor(Color.BLUE);
         // used to draw
         g.fillRect((int)x, (int)y, paddleWidth, paddleHeight);
    }

    public void setPaddlePos(int value) {
        //to update according to mouse inputs 
        this.x = value;

    }

    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,paddleWidth,paddleHeight);
    }
}
