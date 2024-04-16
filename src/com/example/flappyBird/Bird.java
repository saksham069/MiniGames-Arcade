package com.example.flappyBird;
import java.awt.*;


import javax.swing.ImageIcon;


class Bird {
    private int birdWidth;
    private int birdHeight;
    private int birdX;
    private int birdY;
    private Image birdImg;
      
   
    private int velocityY;
    private int gravity;
    

    Image img;

    Bird() {

        birdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        birdWidth = 34;
        birdHeight = 24;
        birdX = 650;
        birdY = 300;

       velocityY = 0;
       gravity = 1;

    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(birdImg, birdX, birdY, birdWidth, birdHeight, null);
        g2d.setColor(Color.RED); // Set color to red for the bounding box
        g2d.drawRect(birdX, birdY, birdWidth, birdHeight); // Draw bounding box

    }

    public void birdMove(){
        velocityY += gravity;
        birdY += velocityY;
        birdY = Math.max(birdY, 0); // the bird will stop after the screen is finished
    }


    public int getBirdY(){
        return birdY;
    }

    public int getBirdX(){
        return birdX;
    }

    public void changeBirdVelocity(){
         velocityY=-9;
    }

    public int getBirdWidth(){
        return birdWidth;
    }

    public int getBirdHeight(){
        return birdHeight;
    }
}
