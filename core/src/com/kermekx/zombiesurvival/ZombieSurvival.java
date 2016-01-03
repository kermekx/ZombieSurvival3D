package com.kermekx.zombiesurvival;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.kermekx.zombiesurvival.engine.renderer.Renderer;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.scene.LoadingScene;

public class ZombieSurvival extends ApplicationAdapter {

	public static ZombieSurvival INSTANCE;
	public static AssetManager manager;

	private int WIDTH;
	private int HEIGHT;
	private boolean fullScreen = false;

	private Renderer renderer;

	@Override
	public void create() {
		INSTANCE = this;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		
		manager = new AssetManager();

		renderer = new Renderer();

		renderer.setScene(new LoadingScene());
	}

	@Override
	public void render() {
		if (Gdx.input.isKeyPressed(Input.Keys.F5))
			setFullScreen(!fullScreen);
			
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}
	
	public void setScene(Scene scene) {
		renderer.setScene(scene);
	}

	private void setFullScreen(boolean fullScreen) {
		if (fullScreen)
			Gdx.graphics.setDisplayMode(WIDTH, HEIGHT, true);
		else
			Gdx.graphics.setDisplayMode(WIDTH / 2, HEIGHT / 2, false);
		this.fullScreen = fullScreen;
	}

}
