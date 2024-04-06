package com.example.brickBreaker;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class Map {
    
    //2d array for bricks
    private int[][] map;
    private double brickHeight, brickWidth;
    private final Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    public final int paddingX=80,paddingY=100;

    public Map(int row , int col){
        map=new int[row][col];

        for(int i=0;i<map.length;i++){
            for(int j=0;j< map[0].length;j++){
                int r=(int)(Math.random()*5+1);
                map[i][j]=r;
            }
        }

        brickHeight=(screenSize.getHeight()/2 - paddingY*1.5)/row;
        brickWidth=(screenSize.getWidth()-2*paddingX)/col;

    }

    public void draw(Graphics2D g){
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j< map[0].length;j++){
                if(map[i][j]>0){

                    if(map[i][j]==1){
                        g.setColor(Color.CYAN);
                    }

                    if(map[i][j]==2){
                        g.setColor(Color.RED);
                    }

                    if(map[i][j]==3){
                        g.setColor(Color.YELLOW);
                    }

                    if(map[i][j]==4){
                        g.setColor(Color.PINK);
                    }

                    if(map[i][j]==5){
                        g.setColor(Color.GREEN);
                    }
                    g.fillRect((int)(j*brickWidth+paddingX), (int)(i*brickHeight+paddingY),(int)brickWidth,(int)brickHeight);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.white);
                    g.drawRect((int)(j*brickWidth+paddingX), (int)(i*brickHeight+paddingY),(int)brickWidth,(int)brickHeight);
                }
                
            }
        }
    }

    public int[][] getMapArray(){return map;}

    public void hitBrick(int row, int col){
        map[row][col]=0;
    }
    
    public double getBrickWidth(){
        return brickWidth;
    }

    public double getBrickHeight(){
        return brickHeight;
    }

    public boolean isWin(){
        boolean isWin=false;
        int bricksRemaining=0;

        for(int i=0;i<map.length;i++){
            for(int j=0;j< map[0].length;j++){
                bricksRemaining+=map[i][j];
            }
        }

        if(bricksRemaining==0){
            isWin=true;
        }

        
        return isWin;
    }
}
