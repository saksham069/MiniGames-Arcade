package com.miniGamesArcade.games.snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class Score {
    private int score;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Score(){
        score=0;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 36);
        g.setFont(font);
        g.drawString("Score: " + score,(int)screenSize.getWidth()-180 ,40);
    }

    public int getScore(){
        return score;
    }

    public void addScore(int value){
        score+=value;
    }
}
