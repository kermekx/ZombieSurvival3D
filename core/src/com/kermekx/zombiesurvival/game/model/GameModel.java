package com.kermekx.zombiesurvival.game.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.kermekx.zombiesurvival.engine.model.ModelManager;

public enum GameModel {
	
	BULLET("misc/bullet");
	
	private static final String TEXTURES_PATH = "model/";
	private static final String IMAGE_FORMAT = ".g3db";

	private final String name;
	private Model model;
	
	private GameModel(String path) {
		name = path;
		load();
	}
	
	private void load() {
		ModelManager.load(TEXTURES_PATH + name + IMAGE_FORMAT);
	}
	
	public Model getModel() {
		if (model == null) {
			model = ModelManager.get(TEXTURES_PATH + name + IMAGE_FORMAT);
		}
		return model;
	}
}