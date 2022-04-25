package com.example.demo;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Pawn extends GameObject{
    private int currPos;
    Pawn(ImageView imageview, int currPos){
        super(imageview);
        this.currPos = currPos;
    }

    void move(int finalPos){
        int y_dist = (finalPos/10) - (currPos/10);
        int x_dist;
        if(((finalPos/10)%2==0 && (currPos/10)%2==0) || ((finalPos/10)%2!=0 && (currPos/10)%2!=0)){
            x_dist = (finalPos%10) - (currPos%10);
        }
        else{
            x_dist = (10-(finalPos%10)+1) - (currPos%10);
        }
        TranslateTransition move = new TranslateTransition(Duration.millis(200),this.getImage());
    }

    public void setCurrPos(int currPos) {
        this.currPos = currPos;
    }

    public int getCurrPos() {
        return currPos;
    }
}
