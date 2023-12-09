package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PauseMenu extends HBox {
    private Button resumeButton;
    private Button saveGameButton;
    private Button homeButton;

    public PauseMenu() {
        initialize();
    }

    private void initialize() {
        setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);"); // Semi-transparent white background

        Text title = new Text("Pause Menu");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setFill(Color.BLACK);

        resumeButton = createButton("Resume");
        saveGameButton = createButton("Save Game");
        homeButton = createButton("Home");

        getChildren().addAll(title, resumeButton, saveGameButton, homeButton);

        setButtonActions();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18;");
        return button;
    }

    private void setButtonActions() {
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Add logic to resume the game
                hidePauseMenu();
            }
        });

        saveGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Add logic to save the game
                // For demonstration purposes, you can print a message
                System.out.println("Game saved!");
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Add logic to go back to the home screen
                // For demonstration purposes, you can print a message
                System.out.println("Going back to the home screen...");
            }
        });
    }

    public void showPauseMenu() {
        setManaged(true);
        setVisible(true);
    }

    public void hidePauseMenu() {
        setManaged(false);
        setVisible(false);
    }
}
