package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class userinterfacepanel extends Pane {
    private final Controlgame controller;
    private Button clickss;

    private Button butmusic;
    private Button butback;

    public userinterfacepanel(Controlgame controller) {
        this.controller = controller;
        setBackground(new Background(new BackgroundImage(Images.background, null, null, BackgroundPosition.CENTER, new BackgroundSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT, true, true, true, true))));
        setPrefSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT);

        clickss = new Button();
        ImageView imageView = new ImageView(Images.startButton);
        imageView.setFitWidth(70); // Set the preferred width of the image
        imageView.setFitHeight(70); // Set the preferred height of the image
        imageView.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        clickss.setStyle("-fx-background-color: transparent;");
        clickss.setGraphic(imageView);

        clickss.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Correct import for ActionEvent
                controller.actionPerformed(event);
            }
        });

        clickss.setPrefSize(70, 70);
        clickss.setLayoutX((Images.BACKGROUND_WIDTH / 2.0) -75); // Use double for layout
        clickss.setLayoutY((Images.BACKGROUND_HEIGHT / 2.0) + 75); // Use double for layout
        getChildren().add(clickss);


//        Button but = new Button();
//        ImageView imgView = new ImageView(pictures.instructions);
//        imgView.setFitWidth(70); // Set the preferred width of the image
//        imgView.setFitHeight(70); // Set the preferred height of the image
//        imgView.setPreserveRatio(true); // Preserve the aspect ratio while resizing
//        but.setStyle("-fx-background-color: transparent;");
//        but.setGraphic(imgView);
//
//        but.setPrefSize(70, 70);
//        but.setLayoutX((double) (pictures.BACKGROUND_WIDTH/2) - 175);
//        but.setLayoutY((double) (pictures.BACKGROUND_HEIGHT/2) + 65);
//        getChildren().add(but);

        butback = new Button();
        ImageView imgback = new ImageView(Images.back);
        imgback.setFitWidth(70); // Set the preferred width of the image
        imgback.setFitHeight(70); // Set the preferred height of the image
        imgback.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butback.setStyle("-fx-background-color: transparent;");
        butback.setGraphic(imgback);
        butback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // Correct import for ActionEvent
                controller.exitbuttonclic();
            }
        });

        butback.setPrefSize(70, 70);
        butback.setLayoutX((double) (Images.BACKGROUND_WIDTH/2) + 125 );
        butback.setLayoutY((double) (Images.BACKGROUND_HEIGHT/2) + 75);
        getChildren().add(butback);

        Button butsave = new Button();
        ImageView imgsave = new ImageView(Images.savegame);
        imgsave.setFitWidth(70); // Set the preferred width of the image
        imgsave.setFitHeight(70); // Set the preferred height of the image
        imgsave.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butsave.setStyle("-fx-background-color: transparent;");
        butsave.setGraphic(imgsave);

        butsave.setPrefSize(70, 70);
        butsave.setLayoutX((double) (Images.BACKGROUND_WIDTH/2) + 25 );
        butsave.setLayoutY((double) (Images.BACKGROUND_HEIGHT/2) + 75);
        getChildren().add(butsave);

        butmusic = new Button();
        ImageView imagView = new ImageView(Images.musicon);
        imagView.setFitWidth(50); // Set the preferred width of the image
        imagView.setFitHeight(50); // Set the preferred height of the image
        imagView.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butmusic.setStyle("-fx-background-color: transparent;");
        butmusic.setGraphic(imagView);

        butmusic.setOnAction(event -> {

            controller.onMusicButtonClick();
        });


        butmusic.setPrefSize(50, 50);
        butmusic.setLayoutX((double) 0);
        butmusic.setLayoutY((double) 0);
        getChildren().add(butmusic);

        Button butsound = new Button();
        ImageView imgsound = new ImageView(Images.soundon);
        imgsound.setFitWidth(50); // Set the preferred width of the image
        imgsound.setFitHeight(50); // Set the preferred height of the image
        imgsound.setPreserveRatio(true); // Preserve the aspect ratio while resizing
        butsound.setStyle("-fx-background-color: transparent;");
        butsound.setGraphic(imgsound);

        butsound.setOnAction(event -> {

            controller.soundclick();
        });
        butsound.setPrefSize(50, 50);
        butsound.setLayoutX((double) 60);
        butsound.setLayoutY((double) 0);
        getChildren().add(butsound);

        ImageView gamename = new ImageView(Images.gamename);
        gamename.setFitWidth(380); // Set the preferred width of the image
        gamename.setFitHeight(450); // Set the preferred height of the image
        gamename.setPreserveRatio(true);

        gamename.setLayoutX(90);
        gamename.setLayoutY(80);
        getChildren().add(gamename);

        ImageView menu = new ImageView(Images.menu);
        menu.setFitWidth(180); // Set the preferred width of the image
        menu.setFitHeight(400); // Set the preferred height of the image


        menu.setLayoutX(0);
        menu.setLayoutY((Images.BACKGROUND_HEIGHT)/2-50);
        getChildren().add(menu);

    }
}

