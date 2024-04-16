package com.example.simonGame;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Panel extends JPanel{
    
    
    
    Blocks block;
     Panel(){
        block = new Blocks();

        
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX(), y = e.getY();

                // g.fillRect(startX, startY, blockWidth, blockHeight);
                // g.fillRect(startX + blockWidth, startY, blockWidth, blockHeight);
                // g.fillRect(startX, startY + blockHeight, blockWidth, blockHeight);
                // g.fillRect(startX + blockWidth, startY + blockHeight, blockWidth, blockHeight);


                if(!block.getcreatingPattern()){
                if(x > block.getstartX() && x < block.getstartX() + block.getblockWidth() && y > block.getstartY() && y < block.getstartY() + block.getblockHeight()) {
                    block.flashed = 1;
                }
                else if(x > block.getstartX() + block.getblockWidth() && x < block.getstartX() + block.getblockWidth() * 2 && y > block.getstartY() && y < block.getstartY() + block.getblockHeight()) {
                    block.flashed = 2;
                }
                else if(x > block.getstartX() && x < block.getstartX() + block.getblockWidth() && y > block.getstartY() + block.getblockHeight() && y < block.getstartY() + block.getblockHeight() * 2) {
                    block.flashed = 3;
                }
                else if(x > block.getstartX() + block.getblockWidth() && x < block.getstartX() + block.getblockWidth() * 2 && y > block.getstartY() + block.getblockHeight() && y < block.getstartY() + block.getblockHeight() * 2) {
                    block.flashed = 4;
                }
            }

           

            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
            
        });

        block.start();
       
     }

     

     


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (block != null) {
            block.draw((Graphics2D) g);
        }
        
    }

    

    

}
