package com.example.stickhero;

import java.util.Random;

public class Engine {
    public static final int mindist = 100;
    public static final int maxdist = 250;
    private String Heroname;
    private boolean overthegame;
    private boolean ismove;
    private RectangularPlatforms one, two;
    private int travelleddist;
    private int lengthofstick;
    private int checkscore;

    private int numcherry;
    private int cherryPos;

    public Engine() {
        this.Heroname = Heroname;
    }

    public void init() {
        one = new RectangularPlatforms();
        two = new RectangularPlatforms();

        assignDistance();
        lengthofstick = 0;
        checkscore = 0;
        numcherry = 0;

        overthegame = false;
        ismove = false;
    }

    private void assignDistance() {
        Random rand = new Random();

        travelleddist = 0;
        while (travelleddist < Engine.mindist)
            travelleddist = rand.nextInt(Engine.maxdist);

        cherryPos = 0;
        while (cherryPos < 50) {
            cherryPos = rand.nextInt(travelleddist - 50);
        }
    }

    public void increasingsticklength() {
        lengthofstick += 3;
    }

    public void checkForGameOver() {
        if (lengthofstick < travelleddist + 5 || travelleddist + two.getGap() + 5 < lengthofstick)
            overthegame = true;
    }

    public void rectnext() {
        one = two;
        two = new RectangularPlatforms();
        assignDistance();

        ismove = false;
        lengthofstick = 0;
        checkscore++;
    }







    public boolean isOverthegame() {
        return overthegame;
    }

    public void setOverthegame(boolean overthegame) {
        this.overthegame = overthegame;
    }

    public boolean isIsmove() {
        return ismove;
    }

    public void setIsmove(boolean ismove) {
        this.ismove = ismove;
    }

    public RectangularPlatforms getOneRect() {
        return one;
    }

    public RectangularPlatforms getTwoRect() {
        return two;
    }

    public int getTravelleddist() {
        return travelleddist;
    }

    public int getLengthofstick() {
        return lengthofstick;
    }

    public String getHeroname() {
        return Heroname;
    }

    public int getCheckscore() {
        return checkscore;
    }

    public int getcherryPos() {
        return cherryPos;
    }

    public void cherryEaten() {
        numcherry++;
    }

    public void decrementCherry(){
        numcherry -= 5;
    }
    public int getcherryNum() {
        return numcherry;
    }
}

