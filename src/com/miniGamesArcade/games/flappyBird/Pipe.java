// package com.miniGamesArcade.flappyBird;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.ArrayList; //to store all the pipes
// import java.util.Random;
// import javax.swing.*;

// public class Pipe {
    
//     private int boardWidth;
//     private int boardHeight;
//     private int xPipe;
//     private int yPipe;
//     private int widthPipe;
//     private int heightPipe;
//     private Image topPipeImg;
//     private Image bottomPipeImg;
    
//     boolean passed ;
    


   
//     Image img;

//     Pipe(){
//         boardWidth = 930; 
//     boardHeight = 850;  
//     xPipe = boardWidth;
//     yPipe =0;
//     widthPipe = 64;
//     heightPipe = 512;
//     passed = false;
//     }

//     Pipe(int value) {
//     if (value==1){
//         topPipeImg = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
//     }else{
//         bottomPipeImg = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();
//     }

    
//     boardWidth = 930; 
//     boardHeight = 850;  
//     xPipe = boardWidth;
//     yPipe =0;
//     widthPipe = 64;
//     heightPipe = 512;
//     passed = false; // to check if our flappy bird has passed the pipe yet?
    
//     }

    



//     public int getPipeY(){
//         return yPipe;
//     }

//     public void setPipeY(int value){
//         yPipe=value;
//     }

//     public int getPipeX(){
//         return xPipe;
//     }

//     public void setPipeX(int value){
//         xPipe+=value;
//     }

//     public int getPipeWidth(){
//         return widthPipe;
//     }

//     public int getPipeHeight(){
//         return heightPipe;
//     }

    


// }
