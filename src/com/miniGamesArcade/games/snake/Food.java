package com.miniGamesArcade.games.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Rectangle;

public class Food {
    private int foodSize=30;
    private Random random;
    private int foodX;
    private int foodY;
    
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Food(){
        random= new Random();
        foodX=random.nextInt((int)screenSize.getWidth()/foodSize)*foodSize;
        foodY=random.nextInt((int)screenSize.getHeight()/foodSize)*foodSize;

    }

    public void draw(Graphics2D g2d){
        
        g2d.setColor(Color.RED);
       
        g2d.fillRect(foodX,foodY , foodSize, foodSize);
    }

    public Rectangle getRect() {
        return new Rectangle(foodX,foodY , foodSize, foodSize);
    }

    public void foodReSpawn(){
        foodX=random.nextInt((int)screenSize.getWidth()/foodSize)*foodSize;
        foodY=random.nextInt((int)screenSize.getHeight()/foodSize)*foodSize;
    }
}


//change colors and try to make the array oval
//set screenside collisions 
