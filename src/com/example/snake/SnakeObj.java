package com.example.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class SnakeObj {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int blockSize=30;
    private int noOfblocks = (int)(screenSize.getWidth()*screenSize.getHeight())/(blockSize*blockSize);
    private int x[] = new int[noOfblocks];
	private int y[] = new int[noOfblocks];
	int bodyParts = 6;

    SnakeObj(){
         
    }

    public void draw(Graphics2D g2d){
        //change colors

        for(int i = 0; i< bodyParts;i++) {
            if(i == 0) {
                g2d.setColor(Color.green);
                g2d.fillRect(x[i], y[i], blockSize, blockSize);
            }
            else {
                g2d.setColor(new Color(45,180,0));
                //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                g2d.fillOval(x[i], y[i], blockSize, blockSize);
            }			
        }
    }

    

    public void update(int value){

        for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}

        switch (value) {
            case 1:
                changeRight();
                break;
            case 2:
                changeLeft();
                break;
            case 3:
                changeUp();
                break;
            case 4:
                changeDown();
                break;
            default:
                break;
        }
    }

    Rectangle getRectHead(){
        return new Rectangle(x[0],y[0],blockSize,blockSize);

    }

    public boolean checkBodyCollisions() {
    //checks if head collides with body
    for(int i = bodyParts;i>1;i--) {
        if((x[0] == x[i])&& (y[0] == y[i])) {
            System.out.println("stuff");
            return false;
        }
    }
    return true;
}

    public void changeUp(){
        y[0] = y[0] - blockSize;
        //add if y ,x greater lesser than screen width then game over apne aap 
    }

    public void changeDown(){
        y[0] = y[0] + blockSize;
    }

    public void changeLeft(){
        x[0] = x[0] - blockSize;
    }

    public void changeRight(){
        x[0] = x[0] + blockSize;
    }
    
    public void setBodyParts(){
        bodyParts+=1;
        System.out.println(bodyParts);
    }


public void checkBorderCollisions(){
    //check if head touches left border
    if(x[0] < 0) {
        x[0]=(int)screenSize.getWidth();
    }
    //check if head touches right border
    if(x[0] > screenSize.getWidth()) {
        x[0]=0;
    }

    //check if head touches top border
    if(y[0] < 0) {
        y[0]=(int)screenSize.getHeight();
    }
    //check if head touches bottom border
    if(y[0] > screenSize.getHeight()) {
        y[0]=0;

    }
}
}

//press(up down left right x , y change bool value if (x)change x , if ( y) change y)