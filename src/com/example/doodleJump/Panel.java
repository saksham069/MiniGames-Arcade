package com.example.doodleJump;

import com.example.pauseMenu.MenuOverlay;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
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
    // private Block block;
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
    private final boolean[] paused;
    private final JFrame parentFrame;
    private MenuOverlay overlay;

    Panel() {
        // PAUSE MENU
        paused = new boolean[] { false };
        parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        overlay = new MenuOverlay(parentFrame, new DoodleJump(), paused);

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
        blocks.add(new Block(screenWidth / 2 - 100 / 2, bY));
        blocks.add(new Block(random.nextInt(2 * 200 + 1 - 100/* block width */) + screenWidth / 2 - 200, bY - 100));
        blocks.add(new Block(random.nextInt(2 * 200 + 1 - 100/* block width */) + screenWidth / 2 - 200, bY - 200));
        blocks.add(new Block(random.nextInt(2 * 200 + 1 - 100/* block width */) + screenWidth / 2 - 200, bY - 300));
        blocks.add(new Block(random.nextInt(2 * 200 + 1 - 100/* block width */) + screenWidth / 2 - 200, bY - 400));
        this.setBackground(Color.BLACK);

        checkCollisionsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (paused[0])
                    continue;
                blocks.forEach((b) -> {
                    if (b.collider.intersects(player.collider) && ySpeed >= 0) {
                        double shift = player.collider.getY() + player.collider.getHeight() - b.collider.getY();
                        blocks.forEach((bb) -> {
                            bb.setY = bb.collider.getY() + shift;
                        });
                        ySpeed = jumpSpeed;
                    }
                });
                if (player.collider.getX() <= screenWidth / 2 - 200 && !moveR) {
                    pX = screenWidth / 2 - 200;
                    moveL = false;
                } else if (player.collider.getX() >= screenWidth / 2 + 200 - pWidth && !moveL) {
                    pX = screenWidth / 2 + 200 - pWidth;
                    moveR = false;
                }
            }
        });

        gameThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (paused[0])
                    continue;
                ySpeed += gravity;
                blocks.forEach((b) -> {
                    b.setY -= ySpeed;
                });
                if ((int) bY - (int) (screenHeight / 2 + 200) > score)
                    score = (int) bY - (int) (screenHeight / 2 + 200); // score ka dekhta hu abhi
                if (moveR)
                    pX += xSpeed;
                if (moveL)
                    pX -= xSpeed;
            }
        });

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        paused[0] = !paused[0];
                        overlay.setVisible(paused[0]);
                        break;
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

        player.setPos(pX, pY, pWidth, pHeight);
        blocks.forEach((b) -> {
            b.collider.setFrame(b.collider.getX(), b.setY, b.collider.getWidth(), b.collider.getHeight());
        }); // yaha place kiya hai to move at a constant predefined rate

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.fill(player.frame);
        g2d.setColor(Color.RED);
        g2d.fill(player.collider);
        blocks.forEach((b) -> {
            g2d.fill(b.collider);
        });

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, (int) screenWidth / 2 - 200, (int) screenHeight);
        g2d.fillRect((int) screenWidth / 2 + 200, 0, (int) screenWidth / 2 - 200, (int) screenHeight);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18)); // set font globally
        String scoreString = "Score: " + score;
        g2d.drawString(scoreString, (int) screenWidth / 2 - g2d.getFontMetrics().stringWidth(scoreString) / 2, 30);
    }
}
