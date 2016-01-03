package com.kermekx.zombiesurvival.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.kermekx.zombiesurvival.engine.drawable.Box;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.texture.TerrainTexture;

public class GameScene extends Scene {

	long time = 43200;

	public GameScene() {
		addDrawable(new Box(0, 0, -0.5f, 4096f, 4096f, 1f, TerrainTexture.GRASS.getTextureRegion(4096, 4096)));
		addDrawable(
				new Box(0, -256f, 32f, 1040f, 16f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024)));
		addDrawable(
				new Box(0, 256, 32f, 1040f, 16f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024)));
		addDrawable(new Box(-512, 0, 32f, 16f, 528f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512)));
		addDrawable(new Box(512, 0, 32f, 16f, 528f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512)));
		addDrawable(new Box(0, 0, 0.5f, 1024f, 512f, 1f, TerrainTexture.DARK_WOOD.getTextureRegion(1024, 512)));
	}

	@Override
	public boolean update(float delta) {
		time += delta * 100;
		getCamera().setTime(time);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
			getCamera().translate(-delta * 100, 0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			getCamera().translate(delta * 100, 0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
			getCamera().translate(0, delta * 100);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			getCamera().translate(0, -delta * 100);
		}

		return true;
	}

}
