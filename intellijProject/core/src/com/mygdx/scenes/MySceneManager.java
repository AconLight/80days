package com.mygdx.scenes;

import boost.SceneManager;

public class MySceneManager extends SceneManager {

    public static Game game;

    @Override
    public void createSceneManager() {
        super.createSceneManager();
        game = new Game();
        addScene(game);
        switchToScene(game);
    }

    @Override
    public void act() {
        super.act();
    }
}
