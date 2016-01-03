package com.kermekx.zombiesurvival.engine.renderer;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.kermekx.zombiesurvival.engine.camera.Camera;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.engine.scene.Scene;

public class Renderer {

	private Scene scene;

	public ModelBatch modelBatch;

	public Renderer() {
		modelBatch = new ModelBatch();
	}

	public void render() {
		if (scene == null)
			return;
		
		float delta = Gdx.graphics.getDeltaTime();
		if (!scene.update(delta))
			return;

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		Camera camera = scene.getCamera();
		List<Drawable> drawables = scene.getDrawables();
		Environment environment = camera.getEnvironment();

		modelBatch.begin(camera.getCamera());

		for (Drawable drawable : drawables) {
			modelBatch.render(drawable.getInstance(), environment);
		}

		modelBatch.end();

		scene.getStage().draw();
	}

	public void resize(int width, int height) {
		if (scene != null)
			scene.getCamera().resize(width, height);
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void dispose() {
		if (scene != null) {
			List<Drawable> drawables = scene.getDrawables();
			for (Drawable drawable : drawables)
				drawable.dispose();
		}

		modelBatch.dispose();
	}

}
