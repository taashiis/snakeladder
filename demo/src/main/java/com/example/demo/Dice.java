package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

public class Dice extends GameObject{
    private final List<Image> faces;
    private int face_num;
    public Dice(List<Image> faces, ImageView curr_face) {
        super(curr_face);
        this.faces = faces;
    }
    public void roll(Indicator indicator){
        indicator.getImage().setOpacity(0);

        face_num = (int)Math.floor(Math.random()*(6)+1);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        for (int i=0; i < faces.size(); i++) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(100*(i+1)),
                    (ActionEvent event) -> this.setImage(faces.get(finalI))
            ));
        }
        timeline.play();
        timeline.setOnFinished(event->{
            this.setImage(faces.get(face_num-1));
            indicator.getImage().setOpacity(1);
        });
    }

    public int getFace_num() {
        return face_num;
    }
}
