package com.example.stickhero;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class Naming extends FlowPane {
    private static final int Lengthofborders = 2;
    private static final int sizemax = 100;

    private Label name;

    public Naming(final Engine engine) {
        super();
        setPadding(new Insets(Lengthofborders));
        setAlignment(Pos.CENTER);

        name = new Label(engine.getHeroname());

        name.setPadding(new Insets(Lengthofborders));
        name.setMaxSize(sizemax, sizemax);

        name.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 22;");

        getChildren().add(name);
    }
}
