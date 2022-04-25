package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

public abstract class BoardElement{
    private int startPos;
    private int endPos;
    BoardElement(int startPos, int endPos){
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public int getStartPos() {
        return startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public abstract TranslateTransition move(Pawn pawn);
}
