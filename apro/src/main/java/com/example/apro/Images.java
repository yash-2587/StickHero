package com.example.apro;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Images {
    public static Image background;
    public static Image startButton;
    public static Image hero;
    public static Image replay;
    public static Image cherry;
    public static Image instructions;
    public static final int BACKGROUND_WIDTH = 500;
    public static final int BACKGROUND_HEIGHT = 600;
    public static final int MARIO_WIDTH = 25; // or any suitable value
    public static final int MARIO_HEIGHT =50; // or any suitable value


    static {

        try {

            background = ImageIO.read(new File("images/background.png"));
            startButton = ImageIO.read(new File("images/StartButton.png"));
            hero= ImageIO.read(new File("images/stand.png"));
            replay = ImageIO.read(new File("images/replay.png"));
            cherry = ImageIO.read(new File("images/cherry.png"));
            instructions = ImageIO.read(new File("images/instruction.png"));

        } catch (IOException e) {

        }
    }

}