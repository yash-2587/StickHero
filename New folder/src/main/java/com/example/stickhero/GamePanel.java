package com.example.stickhero;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GamePanel extends BorderPane {
    private PlayPanel playPanel;
    private StartPanel startPanel;
    private NamePanel namePanel;
    private GameOverPanel gameOverPanel;

    private GameEngine engine;
    private GameController controller;

    public void init(GameEngine engine, GameController controller) {
        this.engine = engine;
        this.controller = controller;

        namePanel = new NamePanel(engine);
        startPanel = new StartPanel(controller);
        playPanel = new PlayPanel(engine, controller);

        playPanel.setOnMouseClicked(controller);
        playPanel.setOnMousePressed(controller);
        playPanel.setOnMouseReleased(controller);

        setCenter(startPanel);
        setBottom(namePanel);

    }

    public void goToGame() {
        setCenter(playPanel);
    }

    public void gameOver() {
        gameOverPanel = new GameOverPanel(engine, controller);
        getChildren().clear();
        setCenter(gameOverPanel);
    }

    public void replay(GameEngine engine, GameController controller) {
        StickHero.startGame();
        setCenter(playPanel);

    }
    public void home(GameEngine engine, GameController controller){

    }

    public void render() {
        goToGame();
    }

    public void gotoinstruct() {
    }
}
