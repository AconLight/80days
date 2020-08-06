package com.mygdx.game;

import assets.AssetLoader;
import boost.GameObjectManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.scenes.MySceneManager;

public class MyGdxGame extends ApplicationAdapter {
	MySceneManager manager;
	@Override
	public void create () {
		ShaderProgram.pedantic = false;
		GameObjectManager.create();
		AssetLoader.load();
		manager = new MySceneManager();
		manager.createSceneManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.act();
	}
}
