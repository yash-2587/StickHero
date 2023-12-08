package com.example.stickhero;

import javafx.scene.layout.BorderPane;

public class Panelediting extends BorderPane {
    private PlayPanel gameplaypanel;
    private userinterfacepanel gamestartpanel;
    private Naming gamenaming;
    private Outpanel gameover;

    private Engine engine;
    private Controlgame controller;

    public void init(Engine engine, Controlgame controller) {
        this.engine = engine;
        this.controller = controller;

        gamenaming = new Naming(engine);
        gamestartpanel = new userinterfacepanel(controller);
        gameplaypanel = new PlayPanel(engine, controller);

        gameplaypanel.setOnMouseClicked(controller);
        gameplaypanel.setOnMousePressed(controller);
        gameplaypanel.setOnMouseReleased(controller);

        setCenter(gamestartpanel);
        setBottom(gamenaming);

    }

    public void goToGame() {
        setCenter(gameplaypanel);
    }

    public void gameOver() {
        gameover = new Outpanel(engine, controller);
        getChildren().clear();
        setCenter(gameover);
    }

    public void replay(Engine engine, Controlgame controller) {
        Main.startGame();
        setCenter(gameplaypanel);

    }
    public void home(Engine engine, Controlgame controller){

    }

    public void render() {
        goToGame();
    }

    public void gotoinstruct() {
    }
}
