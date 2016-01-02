package com.kermekx.zombiesurvival.engine.texture;

import com.badlogic.gdx.graphics.Texture;
import com.kermekx.zombiesurvival.ZombieSurvival;

public class TextureManager {
	
	public static void load(String path) {
		ZombieSurvival.manager.load(path, Texture.class);
	}

	public static Texture get(String path) {
		if (ZombieSurvival.manager.isLoaded(path))
			return ZombieSurvival.manager.get(path, Texture.class);
		return null;
	}
	
}
