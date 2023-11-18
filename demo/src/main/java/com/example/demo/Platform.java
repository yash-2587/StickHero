package com.example.demo;

public class Platform {
    private double width;
    private double height;
    private double xPosition;
    private double yPosition;

    public Platform(double width, double height, double xPosition, double yPosition) {
        this.width = width;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // Getters and Setters
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }
}

