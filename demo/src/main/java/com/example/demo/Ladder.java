package com.example.demo;


import javafx.animation.TranslateTransition;
import javafx.util.Duration;

interface changes{
    TranslateTransition move(Pawn pawn);
}
public class Ladder extends BoardElement implements changes{
    Ladder(int startPos, int endPos) {
        super(startPos, endPos);
    }

    @Override
    public TranslateTransition move(Pawn pawn) {
        TranslateTransition move = new TranslateTransition(Duration.millis(500), pawn.getImage());
        move.setByY(-45*((int)(this.getEndPos()/10) - (int)(this.getStartPos()/10)));
        if( ( ((int)(this.getEndPos()/10)%2==0) && ((int)(this.getStartPos()/10)%2==0) ) || ( ((int)(this.getStartPos()/10)%2!=0) && ((int)(this.getEndPos()/10)%2!=0) ) ){
            if((int)(this.getEndPos()/10)%2==0){
                move.setByX(32.5*((this.getEndPos()%10)-(this.getStartPos()%10)));
            }
            else{
                move.setByX(-32.5*((this.getEndPos()%10)-(this.getStartPos()%10)));
            }
        }
        else{
            int projected_num = 10-(this.getEndPos()%10)+1;
            if(((int)(this.getEndPos()/10)%2!=0)){
                move.setByX(32.5*(projected_num - (this.getStartPos()%10)));
            }
            else{
                move.setByX(-32.5*(projected_num - (this.getStartPos()%10)));
            }
        }
        move.setCycleCount(1);
        move.setAutoReverse(false);
//        move.play();
        pawn.setCurrPos(this.getEndPos());
        return move;
    }
}
