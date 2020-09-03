package com.mygdx.gameObjects;

import assets.AssetLoader;
import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;

import static boost.MyMath.getRotationMatrix;
import static boost.MyMath.multiplyMatrix;

public class Spot extends GameObject {

    SpriteObject spotSprite;

    public float myX, myY;
    public float[] pos = {0, 0, 1};
    public float[] pos2 = {0, 1, 0};

    public Spot(float myX, float myY, float[] pos, float[] pos2) {
        super();
        this.myX = myX;
        this.myY = myY;
        this.pos = pos;
        this.pos2 = pos2;
        spotSprite = AssetLoader.getAsset("dot");
        addActor(spotSprite);
    }

    public Spot(float[] pos, float[] pos2) {
        super();
        this.myX = 0;
        this.myY = 0;
        this.pos = pos;
        this.pos2 = pos2;
        spotSprite = AssetLoader.getAsset("dot2");
        addActor(spotSprite);
    }

    public boolean isOn = true;

    float[] res;

    public void pointUpdate(float[] eulerAngles) {
        float[][] rot = getRotationMatrix(myX, myY);
        float[][] rotAlfa = getRotationMatrix(eulerAngles[0], 0);
        float[][] rotBeta = getRotationMatrix(0, eulerAngles[1]);
        float[][] rotGamma = getRotationMatrix(0, 0, eulerAngles[2]);
        float[] one = {0, 0, 1};
        res = multiplyMatrix(rot, one);
        res = multiplyMatrix(rotGamma, res);
        res = multiplyMatrix(rotBeta, res);
        res = multiplyMatrix(rotAlfa, res);
    }

    public void plot(float radius) {

        float dx = res[0]*radius;
        float dy = res[1]*radius;
        float dz = res[2];

        setPosition(dx, dy);

        if (dz < 0 && isOn) {
            removeActor(spotSprite);
            isOn = false;
        }

        if (dz >= 0 && !isOn) {
            addActor(spotSprite);
            isOn = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
