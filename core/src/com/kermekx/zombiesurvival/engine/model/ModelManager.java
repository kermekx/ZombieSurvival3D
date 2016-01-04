package com.kermekx.zombiesurvival.engine.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.kermekx.zombiesurvival.ZombieSurvival;

public class ModelManager {
	
	public static void load(String path) {
		ZombieSurvival.manager.load(path, Model.class);
	}

	public static Model get(String path) {
		if (ZombieSurvival.manager.isLoaded(path))
			return ZombieSurvival.manager.get(path, Model.class);
		return null;
		
	}
}
