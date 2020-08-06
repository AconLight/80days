package com.mygdx.gameComponents;

import boost.GameComponent;
import boost.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.collisions.CollisionHandler;
import com.mygdx.collisions.CollisionInfo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ExactCollision extends GameComponent {

    ArrayList<CollisionInfo> collidables;
    CollisionInfo main;
    public ExactMovement exactMovement;
    CollisionHandler collisionHandler;

    public ExactCollision(CollisionHandler collisionHandler, CollisionInfo main, ArrayList<CollisionInfo> collidables, ExactMovement exactMovement) {
        this.collidables = collidables;
        this.exactMovement = exactMovement;
        this.collisionHandler = collisionHandler;
        this.main = main;
    }

    @Override
    public void actAfter(float delta) {
        for (CollisionInfo info2: collidables) {
            Vector2 col = collisionHandler.handle(main, info2);
            if (Math.abs(col.x) > 0) {
                exactMovement.eVelocityX = new BigDecimal(0);
            }
            if (Math.abs(col.y) > 0) {
                exactMovement.eVelocityY = new BigDecimal(0);
            }
            exactMovement.ePositionX = exactMovement.ePositionX.add(new BigDecimal(col.x));
            exactMovement.ePositionY = exactMovement.ePositionY.add(new BigDecimal(col.y));
            exactMovement.setePosition();
        }
    }
}
