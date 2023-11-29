package com.example.apro;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
public class PlayPanel extends JPanel {
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

    public PlayPanel(GameEngine engine, GameController controller) {
        this.engine = engine;
        this.controller = controller;
        init();
    }

    public void init() {
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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        moveBackground(g2d);

        g2d.setColor(Color.black);

        AffineTransform def = g2d.getTransform();

        calcRectMove();
        drawRects(g2d);

        //Draw Stick
        calcDegree();
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotateDegree), RECT_START + firstWidth - STICK_WIDTH - 2,
                Images.BACKGROUND_HEIGHT - RECT_HEIGHT);
        g2d.fillRect(RECT_START + firstWidth - STICK_WIDTH - 2, Images.BACKGROUND_HEIGHT - RECT_HEIGHT - engine.getStickLength(),
                STICK_WIDTH, engine.getStickLength());
        g2d.setTransform(old);

        calcDest();
        moveMario();
        drawMario(g2d);

        checkForcherryEaten();
        drawcherry(g2d);

        g2d.setTransform(def);

        checkForGameOver();


        drawScore(g2d);
    }

    private void calcRectMove() {
        if (moveValue >= engine.getDistance() + firstWidth ) {
            controller.nextRect(ischerryEaten);
            init();
        }

        if (rotateDegree == 90 && marioX == dest && !engine.isGameOver()) {
            moveValue += 4;
        }
    }

    private void moveBackground(Graphics2D g2d) {
        if (rotateDegree == 90 && marioX == dest && backgroundMoveValue % 20 != 0 && !engine.isGameOver())
            backgroundMoveValue++;

        AffineTransform old = g2d.getTransform();
        g2d.translate(-backgroundMoveValue, 0);
        g2d.drawImage(Images.background, 0, 0, null);
        g2d.setTransform(old);
    }

    private void drawRects(Graphics2D g2d) {
        g2d.translate(-moveValue, 0);
        g2d.fillRect(RECT_START, Images.BACKGROUND_HEIGHT - RECT_HEIGHT, firstWidth, RECT_HEIGHT);

        if (!engine.isMoving() && rotateDegree == 0 && secondRectPos != RECT_START + firstWidth + engine.getDistance() )
            secondRectPos -= 20;
        if (secondRectPos < RECT_START + firstWidth + engine.getDistance())
            secondRectPos = RECT_START + firstWidth + engine.getDistance();

        g2d.fillRect(secondRectPos,
                Images.BACKGROUND_HEIGHT - RECT_HEIGHT, secondWidth, RECT_HEIGHT);
    }

    private void calcDegree() {
        if (!engine.isMoving())
            return;

        if (rotateDegree < 90) {
            rotateDegree += rotateSpeed / 5;
            rotateSpeed ++;
        } else {
            rotateDegree = 90;
        }
    }




    private void drawMario(Graphics2D g2d) {
        if (marioX <= RECT_START + firstWidth)
            controller.upsideDown = false;
        AffineTransform old = g2d.getTransform();
        g2d.translate(marioX, marioY + Images.MARIO_HEIGHT);
        if (engine.isMoving() && controller.isUpsideDown()) {
            g2d.scale(1, -1);
        }
        if (rotateDegree == 90 && marioX < dest) {
            g2d.drawImage(Images.hero, 0, -Images.MARIO_HEIGHT, null);
            cycleCnt++;
            cycleCnt %= 8;
            if (cycleCnt % 8 == 0) {
                imageCycle++;
                imageCycle %= 4;
            }

        } else {
            g2d.drawImage(Images.hero, 0, -Images.MARIO_HEIGHT, null);
        }

        g2d.setTransform(old);
    }





    private void calcDest() {
        if (engine.isGameOver() && controller.isUpsideDown())
            return;

        if (engine.isGameOver()) {
            dest = RECT_START + firstWidth - Images.MARIO_WIDTH + engine.getStickLength();
        } else {
            dest = RECT_START + firstWidth + engine.getDistance() + secondWidth - 5 - Images.MARIO_WIDTH;
        }
    }




    private void moveMario() {
        if (rotateDegree == 90 && marioX < dest)
            marioX += 2;

        if (marioX > dest)
            marioX = dest;

        if (marioX == dest && engine.isGameOver())
            marioY += 20;

        if (marioY > Images.BACKGROUND_HEIGHT)
            controller.gameOver();
    }


    private void drawcherry(Graphics2D g2d) {
        if (!ischerryEaten) {
            g2d.drawImage(Images.cherry, RECT_START + firstWidth + engine.getcherryPos(),
                    Images.BACKGROUND_HEIGHT - RECT_HEIGHT + 5, null);
        }
    }

    private void checkForcherryEaten() {
        if (controller.isUpsideDown() && marioX + Images.MARIO_WIDTH >= RECT_START + firstWidth + engine.getcherryPos()
                && marioX <= RECT_START + firstWidth + engine.getcherryPos() + 25)
            ischerryEaten = true;
    }


    private void checkForGameOver() {
        if (controller.isUpsideDown() && marioX + Images.MARIO_WIDTH >= RECT_START + firstWidth + engine.getDistance() ) {
            engine.setGameOver(true);
            dest = marioX;
        }
    }





    private void drawScore(Graphics2D g2d) {
        g2d.setFont(new Font("Trattatello", Font.PLAIN, 40));
        g2d.setColor(Color.black);
        g2d.drawString("Score : " + engine.getScore(), 30, 70);
        g2d.drawString("Cherry : " + engine.getcherryNum(), 270, 70);
    }
}

