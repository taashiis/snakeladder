package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {
    private ImageView image;
    GameObject(ImageView image){
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }
}
