package com.mygdx.gameObjects;

import boost.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.backgroundProcess.BackgroundProcess;

import java.util.ArrayList;
import java.util.function.Function;

import static boost.MyMath.getAngles;
import static boost.MyMath.getRotationMatrix;
import static boost.MyMath.multiplyMatrix;

public class Earth extends GameObject {

    ArrayList<Spot> spots;

    public float rotationX, rotationY, rotationZ;

    public float tempMouseX, tempMouseY;

    float radius = 300;

    public float[] pos = {0, 0, 1};
    public float[] pos2 = {0, 1, 0};

    public Earth() {
        super();
        setPosition(800, 800);
        spots = new ArrayList<>();
        generateSpots(radius);
    }

    public void generateSpots(float density) {
        for (Spot spot: spots) {
            removeActor(spot);
        }
        spots.clear();
        float R = 314/2;
        float cx = -0.8f;
        float cy = 0.156f;

        for (float j = 0; j < R*2; j+=300/density *10) {
            for (float i = 0; i < R*2; i+=300/density *10) {
                final float myI = i;
                final float myJ = j;
                BackgroundProcess.addTask(new Function() {
                    @Override
                    public Object apply(Object o) {
                        float zx = (myI - R)/R*4/2;
                        float zy = (myJ - R)/R*4/2;
                        //Gdx.app.log("asd ", "asd " + zx);
                        boolean isJulia = true;
                        for (int a = 0; a < 100; a++) {
                            float xtemp = zx * zx - zy * zy;
                            zy = 2 * zx * zy  + cy;
                            zx = xtemp + cx;
                            if (zx * zx + zy * zy > 4) {
                                isJulia = false;
                                break;
                            }
                        }

                        if (isJulia) {
                            Spot spot = new Spot(1f*myI/314*10, 1f*myJ/314*10, pos, pos2);
                            spot.pointUpdate(getAngles(pos, pos2));
                            spot.plot(radius);
                            spots.add(spot);
                            addActor(spot);
                        }
                        return null;
                    }
                });
            }
        }
        BackgroundProcess.addTask(new Function() {
            @Override
            public Object apply(Object o) {
                Spot spot = new Spot(pos, pos2);
                spots.add(spot);
                addActor(spot);
                pointUpdateSpots();
                plotSpots();
                return null;
            }
        });
    }

    public void rotate(float x, float y) {
        rotationUpdateSpots(x, y, 0);
        pointUpdateSpots();
    }

    public void pointUpdateSpots() {
        float[] eulerAngles = getAngles(pos, pos2);
        for (Spot spot: spots) {
            spot.pointUpdate(eulerAngles);
        }
    }

    public void rotationUpdateSpots(float rotationX, float rotationY, float rotationZ) {
        float[][] rot2 = getRotationMatrix(rotationX, rotationY, rotationZ);
        pos = multiplyMatrix(rot2, pos);
        pos2 = multiplyMatrix(rot2, pos2);
    }

    public void plotSpots() {
        for (Spot spot: spots) {
            spot.plot(radius);
        }
    }

    float prevRadius = 350;

    @Override
    public void act(float delta) {
        super.act(delta);
        
        radius += delta*5;

        if (radius > prevRadius) {
            prevRadius = radius + 50;
            generateSpots(radius*2);
        }

        if (Gdx.input.isTouched()) {
            rotate(-Gdx.input.getDeltaY() / 500f, Gdx.input.getDeltaX() / 500f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            rotate(0.3f*delta, 0);
        } if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            rotate(-0.3f*delta, 0);
        } if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            rotate(0, 0.3f*delta);
        } if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            rotate(0, -0.3f*delta);
        }

        plotSpots();
    }
}
