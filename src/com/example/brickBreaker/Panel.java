package com.example.brickBreaker;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

// we are using the extend method here tp see the difference btw the calls , the previous private jquery object method can be used as well
public class Panel extends JPanel {
    private float x = 100, y = 750;
    private MouseInputs mouseInputs;
    private Color color = new Color(123, 23, 90);

    // defining it here so that both mouselistener and mousemotionlistener knows we
    // are talking about the same object
    public Panel() {
        // listens to the key pressed (for keyboards)
        addKeyListener(new KeyboardInputs(this));
        // using this to pass an object of gamepanel inside keyborard inputs


        // initiallsing the class
        mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

   
    // defining the increase decrease methods for movement , now we just need to
    // call the methods from the keyboard inputs
    public void changeX(int value) {
        this.x += value;
    }

    // to set position using the mouse
    public void setRectPos(int x) {
        this.x = x;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // super is used to access the superclass methods
        // basically says ki hey do all the things that you need to do in that class and
        // then i am gonna start painting myself

        // to change color
        g.setColor(color);
        // used to draw
        g.fillRect((int) x, (int) y, 100, 20);

    }


   
    

}
