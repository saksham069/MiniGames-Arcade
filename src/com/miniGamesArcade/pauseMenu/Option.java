package com.miniGamesArcade.pauseMenu;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Option {
    private final Rectangle selectionArea;
    private Image icon;
    private final FuncInt functionalObj;

    Option(int x, int y, int w, int h, FuncInt task, String iconPath) {
        this.icon = new ImageIcon(getClass().getResource(iconPath)).getImage();
        this.selectionArea = new Rectangle(x, y, w, h);
        this.functionalObj = task;
    }

    Rectangle getSelectionArea() {
        return this.selectionArea;
    }

    Image getIcon() {
        return this.icon;
    }

    void performTask() {
        functionalObj.doSomething();
    }
}