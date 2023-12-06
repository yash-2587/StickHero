package com.example.stickhero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class StickHero extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage stage;
    public static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        StickHero.stage = primaryStage;
        primaryStage.setTitle("Stick Hero");
        stage.getIcons().add(Images.icon);
        startGame();

        StickHero.stage.setResizable(false);
        stage.show();

    }
    public static void startGame(){
        GameEngine engine = new GameEngine();
        GameController controller = new GameController();
        GamePanel panel = new GamePanel();

        engine.init();
        controller.init(engine, panel);
        panel.init(engine, controller);

        controller.start();
        scene = new Scene(panel);
        stage.setScene(scene);
    }
}
