package com.example.snake;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel{
    SnakeObj snake;
    Food food;

    Rectangle foodRect;
    Rectangle snakeHeadRect;
    Rectangle snakeBodyRect;

    boolean running =true;
    int controller;

    public Panel(){
         snake=new SnakeObj();
         food=new Food();

         this.setBackground(Color.BLACK);
         Thread gameThread = new Thread(this::run);
         gameThread.start();
         addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        controller=1;
                        break;
                    case KeyEvent.VK_LEFT:
                        controller=2;
                        break;
                    case KeyEvent.VK_UP:
                        controller=3;
                        break;
                    case KeyEvent.VK_DOWN:
                        controller=4;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }


    public void run() {

        while (running) {
            update();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        snake.update(controller);
        collisionChecker();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        snake.draw(g2d);
        food.draw(g2d);
    }

    public void collisionChecker(){
        snakeHeadRect=snake.getRect();
        foodRect=food.getRect();

        if (snakeHeadRect.intersects(foodRect)) {
            snake.setBodyParts();
            food.foodReSpawn();

    }
}
}
