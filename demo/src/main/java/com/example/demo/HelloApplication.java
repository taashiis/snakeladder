package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static Stage game_stage;
    @Override
    public void start(Stage stage) throws IOException {
        game_stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 721, 603);
        game_stage.setTitle("Snakes & Ladders!");
        game_stage.setScene(scene);
        game_stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}