package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;


public class PlayPanel extends Pane {
    private static final int definestickwidth = 2;
    private static final int Heighofrect = 220;
    private static final int startrect = 50;

    private Engine engine;
    private Controlgame controller;

    private int movevaluebackground = 0;

    private int Onewidth;
    private int twowidth;
    private int secondRectPos;
    private int move;


    private int rotation;
    private int angularspeed;

    private int dest;
    private int StickheroX;
    private int StickheroY;
    private int imageCycle;
    private int cycleCnt;

    private boolean ischerryEaten;

    private Image heroImage;


    public PlayPanel(Engine engine, Controlgame controller) {
        this.engine = engine;
        this.controller = controller;
        init();
        loadImages();
        startGameLoop();
    }

    private void init() {
        movevaluebackground++;
        move = 0;
        secondRectPos = 600;

        rotation = 0;
        angularspeed = 1;

        Onewidth = engine.getOneRect().getGap();
        twowidth = engine.getTwoRect().getGap();

        StickheroX = startrect + Onewidth - 5 - Images.Stickhero_width;
        StickheroY = Images.BACKGROUND_HEIGHT - Heighofrect - Images.stickhero_height;

        imageCycle = 0;
        cycleCnt = 0;
        dest = 0;

        ischerryEaten = false;
    }

    private void loadImages() {
        heroImage = Images.hero;
    }

    private void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    private void update() {
        moveBackground();
        calcRectMove();
        calcDegree();
        calcDest();
        moveMario();
        checkForcherryEaten();
        checkForGameOver();
    }

    private void moveBackground() {
        setBackground(new Background(new BackgroundImage(Images.background, null, null, BackgroundPosition.CENTER, new BackgroundSize(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT, true, true, true, true))));
        if (rotation == 90 && StickheroX == dest && movevaluebackground % 20 != 0 && !engine.isOverthegame())
            movevaluebackground++;
    }

    private void calcRectMove() {
        if (move >= engine.getTravelleddist() + Onewidth) {
            controller.nextplat(ischerryEaten);
            init();
        }

        if (rotation == 90 && StickheroX == dest && !engine.isOverthegame()) {
            move += 4;
        }
    }

    private void calcDegree() {
        if (!engine.isIsmove()) {
            return;
        }

        if (rotation < 90) {
            rotation += angularspeed / 5;
            angularspeed++;
        } else {
            rotation = 90;
        }
    }

    private void calcDest() {
        if (engine.isOverthegame() && controller.isUpdown()) {
            return;
        }

        if (engine.isOverthegame()) {
            dest = startrect + Onewidth - Images.Stickhero_width + engine.getLengthofstick();
        } else {
            dest = startrect + Onewidth + engine.getTravelleddist() + twowidth - 5 - Images.Stickhero_width;
        }
    }

    private void moveMario() {
        if (rotation == 90 && StickheroX < dest) {
            StickheroX += 2;
        }

        if (StickheroX > dest) {
            StickheroX = dest;
        }

        if (StickheroX == dest && engine.isOverthegame()) {
            StickheroY += 20;
        }

        if (StickheroY > Images.BACKGROUND_HEIGHT) {
                engine.setOverthegame(true);
                controller.gameOver();
        }
    }

    public void render() {
        Canvas canvas = new Canvas(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT);
        GraphicsContext g2d = canvas.getGraphicsContext2D();

        g2d.save();

        double pivotX = startrect + Onewidth - definestickwidth - 2;
        double pivotY = Images.BACKGROUND_HEIGHT - Heighofrect;
        Affine oldTransform = g2d.getTransform();
        Affine rotateTransform = new Affine();
        rotateTransform.appendTranslation(pivotX, pivotY);
        rotateTransform.appendRotation(rotation);
        rotateTransform.appendTranslation(-pivotX, -pivotY);
        g2d.setTransform(rotateTransform);


        g2d.fillRect(startrect + Onewidth - definestickwidth - 2, Images.BACKGROUND_HEIGHT - Heighofrect - engine.getLengthofstick(),
                definestickwidth, engine.getLengthofstick());
        g2d.restore();

        drawRects(g2d);
        drawstickhero(g2d);
        Drawcherries(g2d);
        drawfinalscores(g2d);

        getChildren().clear();
        getChildren().add(canvas);
    }

    private void drawRects(GraphicsContext g2d) {
        g2d.setFill(Color.BLACK);
        g2d.translate(-move, 0);
        g2d.fillRect(startrect, Images.BACKGROUND_HEIGHT - Heighofrect, Onewidth, Heighofrect);

        if (!engine.isIsmove() && rotation == 0 && secondRectPos != startrect + Onewidth + engine.getTravelleddist()) {
            secondRectPos -= 20;
        }
        if (secondRectPos < startrect + Onewidth + engine.getTravelleddist()) {
            secondRectPos = startrect + Onewidth + engine.getTravelleddist();
        }

        g2d.fillRect(secondRectPos,
                Images.BACKGROUND_HEIGHT - Heighofrect, twowidth, Heighofrect);
    }

    private void drawstickhero(GraphicsContext g2d) {
        if (StickheroX <= startrect + Onewidth) {
            controller.updown = false;
        }


        if (engine.isIsmove() && controller.isUpdown()) {
            g2d.translate(0, Images.BACKGROUND_HEIGHT + 160);
            g2d.scale(1, -1);
        }

        if (rotation == 90 && StickheroX < dest) {
            g2d.drawImage(heroImage, StickheroX, StickheroY - Images.stickhero_height);
            cycleCnt++;
            cycleCnt %= 8;
            if (cycleCnt % 8 == 0) {
                imageCycle++;
                imageCycle %= 4;
            }
        } else {
            g2d.drawImage(heroImage, StickheroX, StickheroY - Images.stickhero_height);
        }
    }

    private void Drawcherries(GraphicsContext g2d) {
        if (!ischerryEaten) {
            g2d.drawImage(Images.cherry, startrect + Onewidth + engine.getcherryPos(),
                    Images.BACKGROUND_HEIGHT - Heighofrect + 5);
        }
    }

    private void checkForcherryEaten() {
        if (controller.isUpdown() && StickheroX + Images.Stickhero_width >= startrect + Onewidth + engine.getcherryPos()
                && StickheroX <= startrect + Onewidth + engine.getcherryPos() + 25) {
            ischerryEaten = true;
        }
    }

    private void checkForGameOver() {
        if (controller.isUpdown() && StickheroX + Images.Stickhero_width >= startrect + Onewidth + engine.getTravelleddist()) {
                engine.setOverthegame(true);
                dest = StickheroX;
        }
    }

    private void drawfinalscores(GraphicsContext g2d) {
        g2d.setFont(Font.font("", 30));
        g2d.setFill(Color.BLACK);
        g2d.fillText(""+engine.getCheckscore(), 250, 50);
        g2d.drawImage(Images.cherry, 435, 70);
        g2d.fillText("" + engine.getcherryNum(), 400, 90);
    }


}
