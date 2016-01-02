package com.kermekx.zombiesurvival.game.texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kermekx.zombiesurvival.engine.texture.TextureManager;

public enum TerrainTexture {

	DIRT("dirt"), GRASS("grass"), STONE_BRICK_WHITE("stone_brick_white");

	private static final String TEXTURES_PATH = "textures/";
	private static final String IMAGE_FORMAT = ".png";

	private final String name;
	private Texture texture;

	private TerrainTexture(String path) {
		name = path;
		load();
	}

	private void load() {
		TextureManager.load(TEXTURES_PATH + name + IMAGE_FORMAT);
	}

	public Texture getTexture() {
		if (texture == null) {
			texture = TextureManager.get(TEXTURES_PATH + name + IMAGE_FORMAT);
			texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		}
		return texture;
	}
	
	public TextureRegion getTextureRegion(float width, float height) {
		return new TextureRegion(getTexture(), (int) width, (int) height);
	}

}
