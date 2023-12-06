package com.example.stickhero;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class NamePanel extends FlowPane {
    private static final int BORDERS_LENGTH = 2;
    private static final int MAX_SIZE = 100;

    private Label name;

    public NamePanel(final GameEngine engine) {
        super();
        setPadding(new Insets(BORDERS_LENGTH));
        setAlignment(Pos.CENTER);

        name = new Label(engine.getName());

        name.setPadding(new Insets(BORDERS_LENGTH));
        name.setMaxSize(MAX_SIZE, MAX_SIZE);

        name.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 22;");

        getChildren().add(name);
    }
}
