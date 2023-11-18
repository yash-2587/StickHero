package com.example.demo;

public class Cherry {
    private double xPosition;
    private double yPosition;
    private boolean isCollected;

    public Cherry(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isCollected = false;
    }

    public void collect() {
        this.isCollected = true;
    }

    // Getters
    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}
