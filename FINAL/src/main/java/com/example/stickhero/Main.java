package com.example.stickhero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage Stag;
    public static Scene scenegallery;

    public void start(Stage primaryStage) {
        Main.Stag = primaryStage;
        primaryStage.setTitle("Stick Hero");
        Stag.getIcons().add(Images.icon);
        startGame();

        Main.Stag.setResizable(false);
        Stag.show();

    }

    public static void startGame() {
        Engine engine = new Engine();
        Controlgame controller = new Controlgame();
        Panelediting panel = new Panelediting();

        engine.init();
        controller.init(engine, panel);
        panel.init(engine, controller);

        controller.starting();
        scenegallery = new Scene(panel);
        Stag.setScene(scenegallery);
    }

}
