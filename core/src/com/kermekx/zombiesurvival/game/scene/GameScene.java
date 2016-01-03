package com.kermekx.zombiesurvival.game.scene;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.kermekx.zombiesurvival.engine.drawable.Box;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.entity.Bullet;
import com.kermekx.zombiesurvival.game.entity.Decoration;
import com.kermekx.zombiesurvival.game.entity.Entity;
import com.kermekx.zombiesurvival.game.entity.Player;
import com.kermekx.zombiesurvival.game.texture.TerrainTexture;

public class GameScene extends Scene {

	private List<Entity> entities = new ArrayList<Entity>();
	private Player player = new Player(this, new Box(0, 0, 16f, 64f, 64f, 32f, Color.RED));

	private long time = 43200;

	public GameScene() {
		addEntity(player);
		addDrawable(new Box(0, 0, -2f, 4096f, 4096f, 4f, TerrainTexture.GRASS.getTextureRegion(4096, 4096)));
		addEntity(new Decoration(this, new Box(0, -256f, 32f, 1040f, 16f, 64f,
				TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024))));
		addEntity(new Decoration(this,
				new Box(0, 256, 32f, 1040f, 16f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(1024, 1024))));
		addEntity(new Decoration(this,
				new Box(-512, 0, 32f, 16f, 528f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512))));
		addEntity(new Decoration(this,
				new Box(512, 0, 32f, 16f, 528f, 64f, TerrainTexture.STONE_BRICK_WHITE.getTextureRegion(512, 512))));
		addDrawable(new Box(0, 0, 2.5f, 1024f, 512f, 5f, TerrainTexture.DARK_WOOD.getTextureRegion(1024, 512)));
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
			if (player.rotate(delta * 100) == null)
				getCamera().rotate(delta * 100);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (player.rotate(-delta * 100) == null)
				getCamera().rotate(-delta * 100);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
			if (player.translate(0, delta * 100, 0) == null)
				getCamera().translate(0, delta * 100);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (player.translate(0, -delta * 100, 0) == null)
				getCamera().translate(0, -delta * 100);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			float x = player.getHitbox().getCenterX();
			float y = player.getHitbox().getCenterY();
			float rotation = player.getRotation();
			Drawable d = new Box(x,  y,  34, 16, 4, 4, Color.YELLOW);
			d.getInstance().transform.rotate(0, 0, 1, rotation).translate(48, -16, 0);
			addEntity(new Bullet(this, d, 20));
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
