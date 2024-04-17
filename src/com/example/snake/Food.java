package com.example.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Rectangle;

public class Food {

    //declaring food instance variables:- 
    private int foodSize;
    private int foodX;
    private int foodY;
    
    //declaring random
    private Random random;

    //to get screenSize
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Food(){

        //initializing objects using new 
        random= new Random();

        //getting x and y coordinates of food 
        foodX=random.nextInt((int)screenSize.getWidth()/foodSize)*foodSize;
        foodY=random.nextInt((int)screenSize.getHeight()/foodSize)*foodSize;

        //initializing variables 
        foodSize=30;


    }

    //
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


//set screenside collisions 
