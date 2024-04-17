package com.miniGamesArcade.games.brickBreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Paddle {

    private double x,y;
    private int paddleHeight=25, paddleWidth=130;
    private final Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();

    Paddle(){
        //initialising the paddle
        x=(screenSize.getWidth()-paddleWidth)/2;
        y=screenSize.getHeight()-50;

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

        //it will stop paddle from going off the screen
        if(x>screenSize.getWidth()-paddleWidth){
            x=screenSize.getWidth()-paddleWidth;
        }

    }

    public int getWidth(){
        return paddleWidth;
    }

    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,paddleWidth,paddleHeight);
    }

    public int gety(){
        return (int)y;
    }

}

//make paddle outline or make its corner round 
