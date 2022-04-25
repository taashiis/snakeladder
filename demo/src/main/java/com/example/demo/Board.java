package com.example.demo;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;

public class Board extends GameObject{
    Board(ImageView imageView){
        super(imageView);
    }
    public void move(Pawn pawn, int num, boolean checker, List<Ladder> ladders, List<Snake> snakes){
        if(pawn.getCurrPos()==-1){
            if(num==1){
                if(checker)
                {
                    pawn.setCurrPos(1);
                    TranslateTransition move = new TranslateTransition(Duration.millis(500), pawn.getImage());
                    move.setByY((483 - (pawn.getImage().getFitHeight()) / 2) - 504);
                    move.setByX((95.5 - (pawn.getImage().getFitWidth()) / 2) - 94);
                    move.setCycleCount(1);
                    move.setAutoReverse(false);
                    move.play();
                }
                else{
                    pawn.setCurrPos(1);
                    TranslateTransition move = new TranslateTransition(Duration.millis(500), pawn.getImage());
                    move.setByY((483 - (pawn.getImage().getFitHeight()) / 2) - 505);
                    move.setByX((95.5 - (pawn.getImage().getFitWidth()) / 2) - 75);
                    move.setCycleCount(1);
                    move.setAutoReverse(false);
                    move.play();
                }
            }
            return;
        }
        else{
            TranslateTransition x_positive = new TranslateTransition(Duration.millis(500), pawn.getImage());
            TranslateTransition x_negative = new TranslateTransition(Duration.millis(500), pawn.getImage());
            TranslateTransition y_positive = new TranslateTransition(Duration.millis(500), pawn.getImage());
            if(pawn.getCurrPos()%10==0){
                if((pawn.getCurrPos()/10)%2!=0){
                    x_positive.setByX(0);
                    y_positive.setByY(-45);
                    x_negative.setByX(-32.5*(num-1));
                }
                else{
                    x_positive.setByX(0);
                    y_positive.setByY(-45);
                    x_negative.setByX(32.5*(num-1));
                }
            }
            else if( (pawn.getCurrPos()+num) > ((pawn.getCurrPos()/10)+1)*10 ){
                if((pawn.getCurrPos()/10)%2==0){
                    x_positive.setByX(32.5*( (((int)(pawn.getCurrPos()/10)+1)*10) - (pawn.getCurrPos()) ));
                    y_positive.setByY(-45);
                    x_negative.setByX(-32.5*( (pawn.getCurrPos()+num) - (((int)(pawn.getCurrPos()/10)+1)*10) -1));
                }
                else{
                    x_positive.setByX(-32.5*( (((int)(pawn.getCurrPos()/10)+1)*10) - (pawn.getCurrPos()) ));
                    y_positive.setByY(-45);
                    x_negative.setByX(32.5*( (pawn.getCurrPos()+num) - (((int)(pawn.getCurrPos()/10)+1)*10) -1));
                }
            }
            else{
                if((pawn.getCurrPos()/10)%2==0){
                    x_positive.setByX(32.5*num);
                    y_positive.setByY(0);
                    x_negative.setByX(0);
                }
                else{
                    x_positive.setByX(-32.5*num);
                    y_positive.setByY(0);
                    x_negative.setByX(0);
                }
            }
            x_positive.setCycleCount(1);
            x_positive.setAutoReverse(false);
            y_positive.setCycleCount(1);
            y_positive.setAutoReverse(false);
            x_negative.setCycleCount(1);
            x_negative.setAutoReverse(false);
            pawn.setCurrPos(pawn.getCurrPos()+num);
            TranslateTransition ladder_move=null;
            for(Ladder ladder: ladders){
                if(ladder.getStartPos()==pawn.getCurrPos()){
                    ladder_move = ladder.move(pawn);
                    break;
                }
            }
            TranslateTransition snakes_move = null;
            for(Snake snake: snakes){
                if(snake.getStartPos() == pawn.getCurrPos()){
                    snakes_move = snake.move(pawn);
                    break;
                }
            }
            SequentialTransition seqT;
            if(ladder_move==null && snakes_move==null){
                seqT = new SequentialTransition(x_positive, y_positive, x_negative);
            }
            else if(ladder_move!=null && snakes_move==null){
                seqT = new SequentialTransition(x_positive, y_positive, x_negative, ladder_move);
            }
            else{
                seqT = new SequentialTransition(x_positive, y_positive, x_negative, snakes_move);
            }
            seqT.play();
        }
        return;
    }
}
