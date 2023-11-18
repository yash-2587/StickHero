package com.example.demo;

public class ScoreManager {
    private int score;
    private int cherries;

    public ScoreManager() {
        this.score = 0;
        this.cherries = 0;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void collectCherry(int cherryValue) {
        this.cherries += cherryValue;
    }

    public boolean useCherriesForRevival(int cost) {
        if (cherries >= cost) {
            cherries -= cost;
            return true;
        }
        return false;
    }

    // Getters
    public int getScore() {
        return score;
    }

    public int getCherries() {
        return cherries;
    }
}
