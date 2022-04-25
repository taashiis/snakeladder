package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final Stage stage = HelloApplication.game_stage;
    Scene scene;
    boolean firstTimeRolled = false;
    ImageView indicator_image;
    @FXML
    Button dice;
    @FXML
    ImageView dice_face, bluePawn, greenPawn, board_image, indicator_tab;
    @FXML
    TextArea textArea;
    @FXML
    public void gotoHomeScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        String musicFile = "StayTheNight.mp3";     // For example
        scene = new Scene(fxmlLoader.load(), 721, 603);
        stage.setTitle("Snakes And Ladders");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void startGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
        scene = new Scene(fxmlLoader.load(), 477 , 634);
        stage.setTitle("Snakes And Ladders");
        stage.setScene(scene);
        indicator_image = (ImageView)scene.lookup("#indicator");
        textArea = (TextArea)scene.lookup("#final_message");
        indicator_tab = (ImageView)scene.lookup("#indicator_tab");
        Indicator indicator = new Indicator(indicator_image);
        indicator.pointAnimation();
        dice_face  = (ImageView)scene.lookup("#dice_face");
        dice  = (Button)scene.lookup("#dice");
        board_image = (ImageView)scene.lookup("#board");
        bluePawn = (ImageView)scene.lookup("#bluePawn");
        Pawn blue = new Pawn(bluePawn, -1);
        greenPawn = (ImageView)scene.lookup("#greenPawn");

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(3, 21));
        ladders.add(new Ladder(16, 26));
        ladders.add(new Ladder(8, 46));
        ladders.add(new Ladder(29, 33));
        ladders.add(new Ladder(50, 70));
        ladders.add(new Ladder(37 , 65));
        ladders.add(new Ladder(61, 82));
        ladders.add(new Ladder(64, 77));
        ladders.add(new Ladder(76, 95));
        ladders.add(new Ladder(89, 91));

        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(24, 5));
        snakes.add(new Snake(43, 22));
        snakes.add(new Snake(56, 25));
        snakes.add(new Snake(60, 42));
        snakes.add(new Snake(98, 58));
        snakes.add(new Snake(96, 84));
        snakes.add(new Snake(86, 53));
        snakes.add(new Snake(69, 48));
        snakes.add(new Snake(94, 73));
        snakes.add(new Snake(90, 72));
        Pawn green = new Pawn(greenPawn, -1);
        Image dice1 = new Image("1.png");
        Image dice2 = new Image("2.png");
        Image dice3 = new Image("3.png");
        Image dice4 = new Image("4.png");
        Image dice5 = new Image("5.png");
        Image dice6 = new Image("6.png");
        List<Image> faces = new ArrayList<>();
        faces.add(dice1);
        faces.add(dice2);
        faces.add(dice3);
        faces.add(dice4);
        faces.add(dice5);
        faces.add(dice6);
        Player player1 = new Player(green, "Yash", true);
        Player player2 = new Player(blue, "Tanishqa", false);
        Dice dice_button = new Dice(faces, dice_face);
        Board board = new Board(board_image);
        dice.setOnMouseClicked(mouseEvent -> {
            dice_button.roll(indicator);
//            System.out.println(dice_button.getFace_num());
            if(player1.getMyTurn()) {
                if((player1.getPawn().getCurrPos()+dice_button.getFace_num())<=100) {
                    board.move(player1.getPawn(), dice_button.getFace_num(), true, ladders, snakes);
                }
                if(player1.getPawn().getCurrPos()==100){
                    textArea.setText("Congratulation Player1!!! \nYou Won \n press return to replay");
                    textArea.setVisible(true);
                }
                player1.setMyTurn((false));
                player2.setMyTurn(true);
                indicator_tab.setImage((new Image("g.png")));
            }
            else{
                if((player2.getPawn().getCurrPos()+dice_button.getFace_num())<=100) {
                    board.move(player2.getPawn(), dice_button.getFace_num(), false, ladders, snakes);
                }
                if(player2.getPawn().getCurrPos()==100){
                    textArea.setText("Congratulation Player2!!! \nYou Won \n press return to replay");
                    textArea.setVisible(true);
                }
                player2.setMyTurn((false));
                player1.setMyTurn(true);
                indicator_tab.setImage((new Image("b.png")));
            }
        });

        stage.show();
    }
}
