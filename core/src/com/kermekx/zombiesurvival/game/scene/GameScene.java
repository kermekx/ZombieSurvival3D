package com.kermekx.zombiesurvival.game.scene;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.kermekx.zombiesurvival.engine.drawable.Box;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.entity.Decoration;
import com.kermekx.zombiesurvival.game.entity.Entity;
import com.kermekx.zombiesurvival.game.entity.Player;
import com.kermekx.zombiesurvival.game.model.GameModel;
import com.kermekx.zombiesurvival.game.texture.TerrainTexture;

public class GameScene extends Scene {

	private List<Entity> entities = new ArrayList<Entity>();
	private Player player = new Player(this, new Box(0, 0, 1f, 1f, 1f, 2f, Color.RED));

	private long time = 43200;

	public GameScene() {
		addEntity(player);
		addDrawable(new Box(0, 0, -0.1f, 256f, 256f, 0.2f, TerrainTexture.GRASS.getTextureRegion(4096, 4096)));
		
		addEntity(new Decoration(this, new Box(20f, 20f, 0f, GameModel.SCHOOL_BUS_WRECKED.getModel())));
		/**
		addEntity(new Decoration(this,
				new Box(0, -10f, 2f, 10.5f, 0.5f, 4f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024))));
		addEntity(new Decoration(this,
				new Box(0, 10f, 2f, 10.5f, 0.5f, 4f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024))));
		addEntity(new Decoration(this,
				new Box(-5f, 0, 2f, 0.5f, 19.5f, 4f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512))));
		addEntity(new Decoration(this,
				new Box(5f, 0, 2f, 0.5f, 19.5f, 4f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512))));
		addDrawable(new Box(0, 0, 0.1f, 10f, 20f, 0.2f, TerrainTexture.DARK_WOOD.getTextureRegion(1024, 512)));
		 */
	}

	@Override
	public boolean update(float delta) {
		time += delta * 100;
		getCamera().setTime(time);

		List<Entity> deadEntities = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (entity.isAlive())
				entity.update(delta);
			else {
				removeDrawable(entity.getDrawable());
				deadEntities.add(entity);
			}
		}
		entities.removeAll(deadEntities);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
			if (player.rotate(delta) == null)
				getCamera().rotate(delta * Player.ROTATION_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (player.rotate(-delta) == null)
				getCamera().rotate(-delta * Player.ROTATION_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
			if (player.walk(delta) == null)
				getCamera().translate(0, delta * Player.MOVEMENT_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (player.walk(-delta) == null)
				getCamera().translate(0, -delta * Player.MOVEMENT_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			player.use();
		}

		return true;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
		addDrawable(entity.getDrawable());
	}

	public List<Entity> getEntities() {
		return entities;
	}

}
