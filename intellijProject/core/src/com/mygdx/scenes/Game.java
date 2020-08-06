package com.mygdx.scenes;

import assets.AssetLoader;
import boost.MyScene;

public class Game extends MyScene {



    public Game() {
        this.stage.addActor(AssetLoader.getAsset("badlogic"));
    }


    public void act() {
        super.act();

    }
}
