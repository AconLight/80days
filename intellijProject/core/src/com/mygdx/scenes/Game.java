package com.mygdx.scenes;

import assets.AssetLoader;
import boost.MyScene;
import com.mygdx.gameObjects.Earth;

public class Game extends MyScene {



    public Game() {
        this.stage.addActor(new Earth());
    }


    public void act() {
        super.act();

    }
}
