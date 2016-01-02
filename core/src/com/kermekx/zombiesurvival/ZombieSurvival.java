package com.kermekx.zombiesurvival;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.kermekx.zombiesurvival.engine.drawable.Box;
import com.kermekx.zombiesurvival.engine.renderer.Renderer;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.texture.TerrainTexture;

public class ZombieSurvival extends ApplicationAdapter {

	public static AssetManager manager;

	private int WIDTH;
	private int HEIGHT;
	private boolean fullScreen = false;

	private Renderer renderer;

	@Override
	public void create() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		
		manager = new AssetManager();

		renderer = new Renderer();

		TerrainTexture.values();

		manager.finishLoading();

		Scene scene = new Scene() {
		};

		scene.addDrawable(new Box(0, 0, -0.5f, 4096f, 4096f, 1f, TerrainTexture.GRASS.getTextureRegion(4096, 4096)));
		scene.addDrawable(
				new Box(0, -256f, 32f, 1040f, 16f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024)));
		scene.addDrawable(
				new Box(0, 256, 32f, 1040f, 16f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024)));
		scene.addDrawable(
				new Box(-512, 0, 32f, 16f, 528f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512)));
		scene.addDrawable(
				new Box(512, 0, 32f, 16f, 528f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512)));
		scene.addDrawable(new Box(0, 0, 0.5f, 1024f, 512f, 1f, TerrainTexture.DARK_WOOD.getTextureRegion(1024, 512)));

		renderer.setScene(scene);
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

	private void setFullScreen(boolean fullScreen) {
		if (fullScreen)
			Gdx.graphics.setDisplayMode(WIDTH, HEIGHT, true);
		else
			Gdx.graphics.setDisplayMode(WIDTH / 2, HEIGHT / 2, false);
		this.fullScreen = fullScreen;
	}

}
