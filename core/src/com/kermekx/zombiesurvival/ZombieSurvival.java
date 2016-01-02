package com.kermekx.zombiesurvival;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.kermekx.zombiesurvival.engine.drawable.Box;
import com.kermekx.zombiesurvival.engine.renderer.Renderer;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.texture.TerrainTexture;

public class ZombieSurvival extends ApplicationAdapter {
	
	public static AssetManager manager;
	
	private Renderer renderer;
	
	@Override
	public void create() {
		manager = new AssetManager();
		
		renderer = new Renderer();
		
		TerrainTexture.values();
		
		manager.finishLoading();

		Scene scene = new Scene() {
		};
		
		scene.addDrawable(new Box(0, 0, 2048f, 2048f, 1f, TerrainTexture.DIRT.getTextureRegion(2048, 2048)));
		scene.addDrawable(new Box(0, -256, 1024f, 16f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024)));
		scene.addDrawable(new Box(0, 256, 1024f, 16f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024)));
		scene.addDrawable(new Box(-512, 0, 16f, 512f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512)));
		scene.addDrawable(new Box(512, 0, 16f, 512f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512)));
		
		renderer.setScene(scene);
	}

	@Override
	public void render() {
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
}
