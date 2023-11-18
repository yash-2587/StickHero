package com.example.demo;

public class Stick {
    private double length;
    private double xPosition;
    private double yPosition;
    private boolean isExtended;

    public Stick(double xPosition, double yPosition) {
        this.length = 0;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isExtended = false;
    }

    public void extend() {
        // Logic to extend the stick
        this.length += 1; // Example increment, adjust as needed
    }

    // Getters and Setters
    public double getLength() {
        return length;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public void setExtended(boolean extended) {
        isExtended = extended;
    }

    public void setLength(double length) {
        this.length = length;
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
}
