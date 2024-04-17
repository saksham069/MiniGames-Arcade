package com.miniGamesArcade.pauseMenu;

import java.awt.Rectangle;

public class Option {
    private final Rectangle selectionArea;
    private final FuncInt functionalObj;

    Option(int x, int y, int w, int h, FuncInt task){
        this.selectionArea = new Rectangle(x, y, w, h);
        this.functionalObj = task;
    }

    public Rectangle getSelectionArea(){
        return this.selectionArea;
    }

    public void performTask(){
        functionalObj.doSomething();
    }
}