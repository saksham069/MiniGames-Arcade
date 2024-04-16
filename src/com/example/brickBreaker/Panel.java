package com.example.brickBreaker;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Panel extends JPanel {
    private boolean running;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // entities
    Ball ball;
    Paddle paddle;
    Map map;
    Score score;
    int temp=0;

    // fields
    Rectangle ballRect;
    Rectangle paddleRect;

    // inputs
    private MouseInputs mouseInputs;
    
    int speed;

    // constructor
    public Panel() {

        // initiallsing the mouse inputs
        mouseInputs = new MouseInputs();
        addMouseMotionListener(mouseInputs);

        // initialising the loop and the entities
        running = true;
        speed=6;

        ball = new Ball();
        paddle = new Paddle();
        map = new Map(9, 13);
        score= new Score();

        // different thread for the game loop
        // This loop runs continuously, and since it's executed on the Swing event
        // dispatch thread (EDT), it blocks the EDT, preventing Swing from handling user
        // input or updating the UI.

        this.setBackground(Color.BLACK);

        // To fix this freezing issue, you should avoid running long-running tasks on
        // the EDT. Instead, you can use a separate thread to run the game loop.
        Thread gameThread = new Thread(this::run);
        gameThread.start();
        
        // IMPLEEMNT KEYBOARD LATER ON
        // // listens to the key pressed (for keyboards)
        // addKeyListener(new KeyboardInputs(this));
        // // using this to pass an object of gamepanel inside keyborard inputs
    }

    public void run() {
//instead of game over just make the value of running false stooopido
        while (running) {
            // update
            update();

            // render or draw
            // draw();

            // display
            try {
                // too fast
                Thread.sleep(speed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        collisionChecker();
        ball.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // super is used to access the superclass methods
        // basically says ki hey do all the things that you need to do in that class and
        // then i am gonna start painting myself
        Graphics2D g2d = (Graphics2D) g;

        map.draw(g2d);
        ball.draw(g2d);
        paddle.draw(g2d);
        score.draw(g2d);

        if(map.isWin()==true){
            g.setFont(new Font("Courier New",Font.BOLD,50));
            g.setColor(Color.RED);
            g.drawString("YAY YOU WIN !" ,(int)screenSize.getWidth()/2-180,(int)screenSize.getHeight()/2);
            running=false;
        }

        if(isLose()==true){
            g.setFont(new Font("Courier New",Font.BOLD,50));
            g.setColor(Color.RED);
            g.drawString("GAME OVER" ,(int)screenSize.getWidth()/2-180,(int)screenSize.getHeight()/2);
            running=false;
        }
        
    }

    public void collisionChecker() {
        ballRect = ball.getRect();
        paddleRect = paddle.getRect();

        if (ballRect.intersects(paddleRect)) {
            ball.setDy(-ball.getDy());

            //work on the x change logic 

            // if(ball.getx()< mouseX + paddle.getWidth()/4)
            //     ball.setDx(ball.getDx()-0.5);

            // if(ball.getx()< mouseX + paddle.getWidth() && ball.getx()> mouseX + paddle.getWidth()/4 )
            //     ball.setDx(ball.getDx()+0.5);
        }

        A: for (int row = 0; row < map.getMapArray().length; row++) {
            for (int col = 0; col < map.getMapArray()[0].length; col++) {

                if (map.getMapArray()[row][col] > 0) {
                    int brickx = col * (int) map.getBrickWidth() + map.paddingX;
                    int bricky = row * (int) map.getBrickHeight() + map.paddingY;
                    int brickHeight = (int) map.getBrickHeight();
                    int brickWidth = (int) map.getBrickWidth();

                    // can make this method in map only , it will help save a lot of methods TRY IT
                    Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);

                    if (ballRect.intersects(brickRect)) {
                        map.hitBrick(row, col);
                        ball.setDy(-ball.getDy());

                        score.addScore(20);
                        temp++;
                        adjustSpeed();
                        break A;
                    }
                }

            }
        }
    }

    public boolean isLose(){
        boolean isLose=false;

        if(ball.gety()+12> paddle.gety()){
            isLose=true;
        }
        return isLose;
    }

    public void adjustSpeed(){
        if(speed!=2 && temp==10){
            System.out.println(speed);
            speed-=1;
            temp=0;
        }
    }

    // mouse inputs
    private class MouseInputs implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) { 
            paddle.setPaddlePos(e.getX());
        }
    }

}

// there's this built in feature in java where it can tell if one rectangle
// intersects with another rectangle
