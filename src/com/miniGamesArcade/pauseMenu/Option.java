package com.miniGamesArcade.pauseMenu;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

// Represents an option in the pause menu.
public class Option {

    // Defines the area  and image for selecting this option
    private final Rectangle selectionArea;
    private Image icon;
    private final FuncInt functionalObj;

    //Constructs an Option object with the defined parameters.
    Option(int x, int y, int w, int h, FuncInt task, String iconPath) {
        this.icon = new ImageIcon(getClass().getResource(iconPath)).getImage();
        this.selectionArea = new Rectangle(x, y, w, h);
        this.functionalObj = task;
    }

    //Gets the selection area of the option.
    Rectangle getSelectionArea() {
        return this.selectionArea;
    }

    //Gets the icon representing the option.
    Image getIcon() {
        return this.icon;
    }

    //Performs the task associated with the option.
    void performTask() {
        functionalObj.doSomething();
    }
}