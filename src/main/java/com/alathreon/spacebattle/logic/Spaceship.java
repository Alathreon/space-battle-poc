package com.alathreon.spacebattle.logic;

public class Spaceship {
    private final Player player;
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private double accelerationMagnitude;
    private double accelerationAngle;

    public Spaceship(Player player, double x, double y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double y() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double accelerationMagnitude() {
        return accelerationMagnitude;
    }

    public void setAccelerationMagnitude(double accelerationMagnitude) {
        this.accelerationMagnitude = accelerationMagnitude;
    }

    public double accelerationAngle() {
        return accelerationAngle;
    }

    public void setAccelerationAngle(double accelerationAngle) {
        this.accelerationAngle = accelerationAngle;
    }

    public void tick() {
        player.tick(this);
        speedX += accelerationMagnitude * Math.cos(accelerationAngle);
        speedY += accelerationMagnitude * Math.sin(accelerationAngle);
        x += speedX;
        y += speedY;
    }
}
