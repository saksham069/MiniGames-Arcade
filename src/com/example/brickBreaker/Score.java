package com.example.brickBreaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score {
    private int score;

    Score(){
        score=0;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 36);
        g.setFont(font);
        g.drawString("Score: " + score, 40,40);
    }

    public int getScore(){
        return score;
    }

    public void addScore(int value){
        score+=value;
    }
}
