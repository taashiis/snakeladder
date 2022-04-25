package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Indicator extends GameObject{
    Indicator(ImageView imageview){
        super(imageview);
    }
    public TranslateTransition pointAnimation(){
        TranslateTransition move = new TranslateTransition(Duration.millis(520), this.getImage());
        move.setByY((-1)*20);
        move.setCycleCount(Animation.INDEFINITE);
        move.setAutoReverse(true);
        move.play();
        return move;
    }
}
