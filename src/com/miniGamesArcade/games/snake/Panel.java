package com.miniGamesArcade.games.snake;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.miniGamesArcade.pauseMenu.MenuOverlay;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel{
    SnakeObj snake;
    Food food;
    Score score;

    Rectangle foodRect;
    Rectangle snakeHeadRect;
    Rectangle[] snakeBodyRect;

    int speed;
    boolean running =true;
    int directionController;;
    
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final boolean[] paused;
    private final JFrame parentFrame;
    private MenuOverlay overlay;

    public Panel(){
        // PAUSE MENU
        paused = new boolean[] { false };
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        overlay = new MenuOverlay(parentFrame, new Snake(), paused);

         snake=new SnakeObj();
         food=new Food();
         score= new Score();

         this.setBackground(Color.BLACK);
         Thread gameThread = new Thread(this::run);
         gameThread.start();
         directionController=1;
         speed=100;
         addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (directionController!=2){
                            directionController=1;}
                        break;

                    case KeyEvent.VK_LEFT:
                    if (directionController!=1){
                            directionController=2;}
                        break;

                    case KeyEvent.VK_UP:
                    if (directionController!=4){
                            directionController=3;}
                        break;

                    case KeyEvent.VK_DOWN:
                    if (directionController!=3){
                            directionController=4;}
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
                Thread.sleep(speed);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        snake.update(directionController);
        collisionChecker();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        snake.draw(g2d);
        food.draw(g2d);
        score.draw(g2d);

        if(running==false){
            g.setFont(new Font("Courier New",Font.BOLD,70));
            g.setColor(Color.RED);
            g.drawString("GAME OVER" ,(int)screenSize.getWidth()/2-180,(int)screenSize.getHeight()/2);
            running=false;
        }
    }

    public void collisionChecker(){
        snakeHeadRect=snake.getRectHead();
        foodRect=food.getRect();

        if (snakeHeadRect.intersects(foodRect)) {
            snake.setBodyParts();
            food.foodReSpawn(); 
            score.addScore(20);
            adjustSpeed();
        }

        running=snake.checkBodyCollisions();
        snake.checkBorderCollisions();
    }

    public void adjustSpeed(){
        if(speed!=20){
            System.out.println(speed);
            speed-=3;
        }
    }
}


