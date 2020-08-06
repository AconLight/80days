package com.mygdx.gameComponents;

import boost.GameComponent;
import boost.GameObject;

import java.math.BigDecimal;

public class ExactMovementSteering extends GameComponent {

    public ExactMovement exactMovement;
    float dragFactor = 0.8f;
    int accX = 0, accY = 0;
    float accFactor = 2000f;

    public ExactMovementSteering(GameObject gameObject, ExactMovement exactMovement) {
        this.gameObject = gameObject;
        this.exactMovement = exactMovement;
    }

    public ExactMovementSteering(GameObject gameObject, ExactMovement exactMovement, float accFactor, float dragFactor) {
        this.gameObject = gameObject;
        this.exactMovement = exactMovement;
        this.dragFactor = dragFactor;
        this.accFactor = accFactor;
    }

    @Override
    public void act(float delta) {
        exactMovement.eVelocityX = exactMovement.eVelocityX.multiply(new BigDecimal(dragFactor));
        exactMovement.eVelocityY = exactMovement.eVelocityY.multiply(new BigDecimal(dragFactor));
        exactMovement.eAccelerationX = new BigDecimal(accX*accFactor).add(exactMovement.eAccelerationX);
        exactMovement.eAccelerationY = new BigDecimal(accY*accFactor).add(exactMovement.eAccelerationY);
    }

    public void pressDirection(int x, int y) {
        accX = x;
        accY = y;
    }
}
