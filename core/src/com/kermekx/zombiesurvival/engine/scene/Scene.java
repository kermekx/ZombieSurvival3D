package com.kermekx.zombiesurvival.engine.scene;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kermekx.zombiesurvival.engine.camera.Camera;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;

public abstract class Scene {
	
	protected Stage stage = new Stage();
	protected Camera camera = new Camera();
	
	private List<Drawable> drawables = new ArrayList<Drawable>();
	
	public Camera getCamera() {
		return camera;
	}
	
	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}
	
	public void removeDrawable(Drawable drawable) {
		drawables.remove(drawable);
	}
	
	public List<Drawable> getDrawables() {
		return drawables;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public abstract boolean update(float delta);

}
