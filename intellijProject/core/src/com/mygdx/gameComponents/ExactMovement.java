package com.mygdx.gameComponents;

import boost.GameComponent;
import boost.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.math.BigDecimal;

public class ExactMovement extends GameComponent {

    public BigDecimal ePositionX, eVelocityX, eAccelerationX, ePositionY, eVelocityY, eAccelerationY, eConstAccelerationY;

    public ExactMovement(GameObject gameObject, int ePositionX, int ePositionY, int eVelocityX, int eVelocityY, int eAccelerationX, int eAccelerationY) {
        this.gameObject = gameObject;
        gameObject.setPosition(ePositionX, ePositionY);
        this.ePositionX = new BigDecimal(ePositionX);
        this.ePositionY = new BigDecimal(ePositionY);
        this.eVelocityX = new BigDecimal(eVelocityX);
        this.eVelocityY = new BigDecimal(eVelocityY);
        this.eAccelerationX = new BigDecimal(eAccelerationX);
        this.eAccelerationY = new BigDecimal(eAccelerationY);
        this.eConstAccelerationY = new BigDecimal(eAccelerationY);
    }

    private static int castVal = 1000;
    private static float castValF = 1000;

    @Override
    public void actBefore(float delta) {
        this.eAccelerationX = new BigDecimal(0);
        this.eAccelerationY = eConstAccelerationY.add(new BigDecimal(0));
//        Gdx.app.log("ExactMovement", "actBefore");
    }

    @Override
    public void actAfter(float delta) {
//        Gdx.app.log("ExactMovement", "actAfter");
        delta = 0.03f;
        eVelocityX = eVelocityX.add(eAccelerationX.multiply(new BigDecimal(delta)));
        eVelocityY = eVelocityY.add(eAccelerationY.add(eConstAccelerationY).multiply(new BigDecimal(delta)));
        ePositionX = ePositionX.add(eVelocityX.multiply(new BigDecimal(delta)));
        ePositionY = ePositionY.add(eVelocityY.multiply(new BigDecimal(delta)));
        setePosition();
    }

    public void setePosition() {
        gameObject.setPosition(((int)(ePositionX.floatValue()*castVal))/castValF, ((int)(ePositionY.floatValue()*castVal))/castValF);
    }
}
