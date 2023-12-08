package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controlgame implements EventHandler<MouseEvent> {
    public static final int framespersecond = 100;
    private Engine engine;
    private Panelediting panel;
    private boolean increase;
    private boolean run;
    public boolean updown;
    private ImageView btnSound;

    private ImageView btnMusic;

    public void init(Engine engine, Panelediting panel) {
        this.engine = engine;
        this.panel = panel;
    }

    public void starting() {
        AnimationTimer gamingloop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                run = true;
                while (run) {
                    panel.render();
                    try {
                        Thread.sleep(1000 / Controlgame.framespersecond);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }



    public void nextplat(boolean ischerryEaten) {
        if (ischerryEaten)
            engine.cherryEaten();
        engine.rectnext();
    }

    public void gameOver() {
        panel.gameOver();
    }

    public void replay() {
        init(engine, panel);
        panel.replay(engine, this);
    }

    public boolean isUpdown() {
        return updown;
    }

    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
            checkmouseclick();
        if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
            mousetapreleased();
        else if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
            updown = !updown;
    }


    public void checkmouseclick() {
        if (engine.isIsmove()) {
            return;
        }

        Thread incstick = new Thread(() -> {
            increase = true;
            while (increase) {
                engine.increasingsticklength();
                try {
                    Thread.sleep(1000 / Controlgame.framespersecond);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        incstick.start();
    }

    public void mousetapreleased() {
        increase= false;
        engine.setIsmove(true);
        engine.checkForGameOver();
    }

    public void exitbuttonclic() {
        System.exit(0);
    }
    public void soundclick() {
        Audio.onSoundButtonClick(btnSound);
    }

    public void onMusicButtonClick() {
        Audio.onMusicButtonClick(btnMusic,true);
    }
    public void actionPerformed(ActionEvent event) {
        panel.goToGame();
    }
    public void instruct(ActionEvent event) {
        panel.gotoinstruct();
    }
}