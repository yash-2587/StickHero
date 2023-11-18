package com.example.demo;

public class StickHero {
    private double xPosition;
    private double yPosition;

    public StickHero(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        // Initialize sprite image here
    }

    // Method to flip the character, if needed
    public void flip() {
        // Flip logic
    }

    // Getters and Setters
    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

}

