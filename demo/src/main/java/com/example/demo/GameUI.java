package com.example.demo;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class GameUI {
    private GameController gameController;
    private Pane gamePane;
    private ImageView stickHeroView;
    private Rectangle stickView;
    private ImageView cherryView; // Assuming there's only one cherry at a time for simplicity

    public GameUI(GameController gameController) {
        this.gameController = gameController;
        this.gamePane = new Pane();
        initializeUI();
    }

    private void initializeUI() {
        // Initialize and add platforms
        for (Platform platform : gameController.getPlatforms()) {
            Rectangle platformView = new Rectangle(
                    platform.getWidth(),
                    platform.getHeight()
            );
            platformView.setX(platform.getXPosition());
            platformView.setY(platform.getYPosition());
            gamePane.getChildren().add(platformView);
        }

        // Initialize and add the stick
        stickView = new Rectangle(5, 0); // Width 5, length 0 initially
        stickView.setX(gameController.getStick().getxPosition());
        stickView.setY(gameController.getStick().getyPosition());
        gamePane.getChildren().add(stickView);

        // Initialize and add the stick hero
        stickHeroView.setX(gameController.getStickHero().getXPosition());
        stickHeroView.setY(gameController.getStickHero().getYPosition());
        gamePane.getChildren().add(stickHeroView);

        // Initialize and add the cherry
        cherryView = new ImageView(new Image("@assets/cherry.png")); // Replace with the actual path
//        cherryView.setX(gameController.getCherry().getXPosition());
//        cherryView.setY(gameController.getCherry().getYPosition());
        gamePane.getChildren().add(cherryView);
    }

    public void update() {
        // Update stick length and position
        stickView.setHeight(gameController.getStick().getLength());

        // Update stick hero position
        stickHeroView.setX(gameController.getStickHero().getXPosition());
        stickHeroView.setY(gameController.getStickHero().getYPosition());

        // Update cherry visibility and position
//        Cherry cherry = gameController.getCherry();
//        cherryView.setVisible(!cherry.isCollected());
//        cherryView.setX(cherry.getXPosition());
//        cherryView.setY(cherry.getYPosition());

        // Additional updates based on game state...
    }

    public Pane getLayout() {
        return gamePane;
    }
}

