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
    private static final int STICK_WIDTH = 3;
    private static final int RECT_HEIGHT = 220;
    private static final int RECT_START = 50;

    private GameEngine engine;
    private GameController controller;

    private int backgroundMoveValue = 0;

    private int firstWidth;
    private int secondWidth;
    private int secondRectPos;
    private int moveValue;

    private int rotateDegree;
    private int rotateSpeed;

    private int dest;
    private int marioX;
    private int marioY;
    private int imageCycle;
    private int cycleCnt;

    private boolean ischerryEaten;

    private Image heroImage;


    public PlayPanel(GameEngine engine, GameController controller) {
        this.engine = engine;
        this.controller = controller;
        init();
        loadImages();
        startGameLoop();
    }

    private void init() {
        backgroundMoveValue++;
        moveValue = 0;
        secondRectPos = 600;

        rotateDegree = 0;
        rotateSpeed = 1;

        firstWidth = engine.getFirstRect().getWidth();
        secondWidth = engine.getSecondRect().getWidth();

        marioX = RECT_START + firstWidth - 5 - Images.MARIO_WIDTH;
        marioY = Images.BACKGROUND_HEIGHT - RECT_HEIGHT - Images.MARIO_HEIGHT;

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
        if (rotateDegree == 90 && marioX == dest && backgroundMoveValue % 20 != 0 && !engine.isGameOver())
            backgroundMoveValue++;
    }

    private void calcRectMove() {
        if (moveValue >= engine.getDistance() + firstWidth) {
            controller.nextRect(ischerryEaten);
            init();
        }

        if (rotateDegree == 90 && marioX == dest && !engine.isGameOver()) {
            moveValue += 4;
        }
    }

    private void calcDegree() {
        if (!engine.isMoving()) {
            return;
        }

        if (rotateDegree < 90) {
            rotateDegree += rotateSpeed / 5;
            rotateSpeed++;
        } else {
            rotateDegree = 90;
        }
    }

    private void calcDest() {
        if (engine.isGameOver() && controller.isUpsideDown()) {
            return;
        }

        if (engine.isGameOver()) {
            dest = RECT_START + firstWidth - Images.MARIO_WIDTH + engine.getStickLength();
        } else {
            dest = RECT_START + firstWidth + engine.getDistance() + secondWidth - 5 - Images.MARIO_WIDTH;
        }
    }

    private void moveMario() {
        if (rotateDegree == 90 && marioX < dest) {
            marioX += 2;
        }

        if (marioX > dest) {
            marioX = dest;
        }

        if (marioX == dest && engine.isGameOver()) {
            marioY += 20;
        }

        if (marioY > Images.BACKGROUND_HEIGHT) {
                engine.setGameOver(true);
                controller.gameOver();
        }
    }

    public void render() {
        Canvas canvas = new Canvas(Images.BACKGROUND_WIDTH, Images.BACKGROUND_HEIGHT);
        GraphicsContext g2d = canvas.getGraphicsContext2D();

        g2d.save();

        double pivotX = RECT_START + firstWidth - STICK_WIDTH - 2;
        double pivotY = Images.BACKGROUND_HEIGHT - RECT_HEIGHT;
        Affine oldTransform = g2d.getTransform();
        Affine rotateTransform = new Affine();
        rotateTransform.appendTranslation(pivotX, pivotY);
        rotateTransform.appendRotation(rotateDegree);
        rotateTransform.appendTranslation(-pivotX, -pivotY);
        g2d.setTransform(rotateTransform);


        g2d.fillRect(RECT_START + firstWidth - STICK_WIDTH - 2, Images.BACKGROUND_HEIGHT - RECT_HEIGHT - engine.getStickLength(),
                STICK_WIDTH, engine.getStickLength());
        g2d.restore();

        drawRects(g2d);
        drawMario(g2d);
        drawcherry(g2d);
        drawScore(g2d);

        getChildren().clear();
        getChildren().add(canvas);
    }

    private void drawRects(GraphicsContext g2d) {
        g2d.setFill(Color.BLACK);
        g2d.translate(-moveValue, 0);
        g2d.fillRect(RECT_START, Images.BACKGROUND_HEIGHT - RECT_HEIGHT, firstWidth, RECT_HEIGHT);

        if (!engine.isMoving() && rotateDegree == 0 && secondRectPos != RECT_START + firstWidth + engine.getDistance()) {
            secondRectPos -= 20;
        }
        if (secondRectPos < RECT_START + firstWidth + engine.getDistance()) {
            secondRectPos = RECT_START + firstWidth + engine.getDistance();
        }

        g2d.fillRect(secondRectPos,
                Images.BACKGROUND_HEIGHT - RECT_HEIGHT, secondWidth, RECT_HEIGHT);
    }

    private void drawMario(GraphicsContext g2d) {
        if (marioX <= RECT_START + firstWidth) {
            controller.upsideDown = false;
        }


        if (engine.isMoving() && controller.isUpsideDown()) {
            g2d.translate(0, Images.BACKGROUND_HEIGHT + 160);
            g2d.scale(1, -1);
        }

        if (rotateDegree == 90 && marioX < dest) {
            g2d.drawImage(heroImage, marioX, marioY - Images.MARIO_HEIGHT);
            cycleCnt++;
            cycleCnt %= 8;
            if (cycleCnt % 8 == 0) {
                imageCycle++;
                imageCycle %= 4;
            }
        } else {
            g2d.drawImage(heroImage, marioX, marioY - Images.MARIO_HEIGHT);
        }
    }

    private void drawcherry(GraphicsContext g2d) {
        if (!ischerryEaten) {
            g2d.drawImage(Images.cherry, RECT_START + firstWidth + engine.getcherryPos(),
                    Images.BACKGROUND_HEIGHT - RECT_HEIGHT + 5);
        }
    }

    private void checkForcherryEaten() {
        if (controller.isUpsideDown() && marioX + Images.MARIO_WIDTH >= RECT_START + firstWidth + engine.getcherryPos()
                && marioX <= RECT_START + firstWidth + engine.getcherryPos() + 25) {
            ischerryEaten = true;
        }
    }

    private void checkForGameOver() {
        if (controller.isUpsideDown() && marioX + Images.MARIO_WIDTH >= RECT_START + firstWidth + engine.getDistance()) {
                engine.setGameOver(true);
                dest = marioX;
        }
    }

    private void drawScore(GraphicsContext g2d) {
        g2d.setFont(Font.font("", 30));
        g2d.setFill(Color.BLACK);
        g2d.fillText(""+engine.getScore(), 250, 50);
        g2d.drawImage(Images.cherry, 435, 70);
        g2d.fillText("" + engine.getcherryNum(), 400, 90);
    }


}
