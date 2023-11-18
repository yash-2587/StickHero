package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class Main extends Application {
    @Override

    public void start(Stage stage) throws IOException {
//        GameController gameController = new GameController();
//        GameUI gameUI = new GameUI(gameController);
        stage.setTitle("Stick Hero");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/icon.jpg"))));
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setScene(scene);
        stage.show();
//        gameController.initializeGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

