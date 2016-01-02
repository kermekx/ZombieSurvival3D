package com.kermekx.zombiesurvival.engine.scene;

import java.util.ArrayList;
import java.util.List;

import com.kermekx.zombiesurvival.engine.camera.Camera;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;

public abstract class Scene {
	
	Camera camera = new Camera();
	
	private List<Drawable> drawables = new ArrayList<Drawable>();
	
	public Camera getCamera() {
		return camera;
	}
	
	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}
	
	public List<Drawable> getDrawables() {
		return drawables;
	}

}
