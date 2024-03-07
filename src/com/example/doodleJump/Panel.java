package com.example.doodleJump;

import javax.swing.JPanel;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Random;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Panel extends JPanel { // make final width and hieght etc args in block and player and make static
                             // getters for them // set min and max constraints for walls
    private final Dimension screenSize;
    private final double screenHeight, screenWidth;
    private Player player;
    private Deque<Block> blocks; // deque for blocks
    private Block block;
    private int nBlock; // number of total blocks spawned
    private final Random random;
    private final double xSpeed = 10;
    private double ySpeed = 0;
    private final double jumpSpeed = -10;
    private final double gravity = 0.3;
    private boolean moveR;
    private boolean moveL;
    private final double pY, pWidth, pHeight;
    private double pX;
    private double bY;
    private int score;
    private Thread gameThread;
    private Thread checkCollisionsThread;

    Panel() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.getHeight();
        screenWidth = screenSize.getWidth();

        score = 0;

        pWidth = 50;
        pHeight = 50;
        pX = screenWidth / 2 - pWidth / 2;
        pY = screenHeight / 2 - pHeight / 2;
        player = new Player((int) pX, (int) pY, (int) pWidth, (int) pHeight);

        random = new Random();
        bY = screenHeight / 2 + 100;
        blocks = new ArrayDeque<>();
        block = new Block(screenWidth / 2 - 100 / 2, bY);
        blocks.add(new Block(random.nextInt(2 * 200 + 1 - (int) block.collider.getWidth()) + screenWidth / 2 - 200,
                bY - 100));
        this.setBackground(Color.BLACK);

        checkCollisionsThread = new Thread(() -> {
            while (true) {

                if (block.collider.intersects(player.collider)) {
                    // pY = block.collider.getY() - player.collider.getHeight();
                    bY = player.collider.getY() + player.collider.getHeight();
                    ySpeed = jumpSpeed;
                }
                if (player.collider.getX() <= screenWidth / 2 - 200) {
                    pX = screenWidth / 2 - 200;
                } else if (player.collider.getX() >= screenWidth / 2 + 200 - pWidth) {
                    pX = screenWidth / 2 + 200 - pWidth;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        gameThread = new Thread(() -> {
            while (true) {
                if ((int) bY - (int) (screenHeight / 2 + 200) > score)
                    score = (int) bY - (int) (screenHeight / 2 + 200);
                ySpeed += gravity;
                bY -= ySpeed; // +=
                if (moveR)
                    pX += xSpeed;
                if (moveL)
                    pX -= xSpeed;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        moveR = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        moveL = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        moveR = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        moveL = false;
                        break;
                    default:
                        break;
                }
            }
        });

        gameThread.start();
        checkCollisionsThread.start();
        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                moveL = false;
                moveR = false;
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.collider.setFrame(pX, pY, pWidth, pHeight);
        block.collider.setFrame(block.collider.getX(), bY, block.collider.getWidth(),
                block.collider.getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.fill(player.collider);
        g2d.setColor(Color.RED);
        g2d.fill(block.collider);
        g2d.fill(blocks.getLast().collider);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, (int) screenWidth / 2 - 200, (int) screenHeight);
        g2d.fillRect((int) screenWidth / 2 + 200, 0, (int) screenWidth / 2 - 200, (int) screenHeight);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18)); // set font globally
        String scoreString = "Score: " + score;
        g2d.drawString(scoreString, (int) screenWidth / 2 - g2d.getFontMetrics().stringWidth(scoreString) / 2, 30);
    }
}
