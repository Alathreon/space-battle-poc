package com.alathreon.spacebattle.logic;

public class HumanPlayer implements Player {
    private boolean hasTarget;
    private double targetX;
    private double targetY;

    @Override
    public void tick(Spaceship spaceship) {
        if(hasTarget) {
            spaceship.setAccelerationMagnitude(0.01);
            spaceship.setAccelerationAngle(Math.atan2(targetY - spaceship.y(), targetX - spaceship.x()));
        } else {
            spaceship.setAccelerationMagnitude(0);
        }
    }

    public void setTarget(double targetX, double targetY) {
        this.hasTarget = true;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void setNoTarget() {
        this.hasTarget = false;
    }
}
