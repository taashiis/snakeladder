package com.example.demo;

import com.example.demo.GameObject;
import javafx.scene.image.ImageView;

public class Player extends GameObject {
    private Pawn pawn;
    private String name;
    private boolean myTurn;

    Player(Pawn pawn, String name, boolean myTurn) {
        super(pawn.getImage());
        this.pawn = pawn;
        this.name = name;
        this.myTurn = myTurn;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
    public boolean getMyTurn(){
        return myTurn;
    }

    public String getName() {
        return name;
    }
}
