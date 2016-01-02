package com.kermekx.zombiesurvival.engine.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;

public class Camera {

	private PerspectiveCamera camera;
	private final Environment environment;
	private DirectionalLight sun = new  DirectionalLight();
	
	private float x = 0, y = 0;
	
	public Camera() {
		camera = new PerspectiveCamera(512, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.near = 1f;
		camera.far = 300f;
		setPosition(x, y);
		
		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(sun);
        //environment.add(new PointLight().set(1f, 0f, 0f, 1000, 0, -10, 100000f));
        
        setTime(43200);
	}

	public PerspectiveCamera getCamera() {
		return camera;
	}
	
	public Environment getEnvironment() {
		return environment;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		camera.position.set(x, y, 256f);
		camera.lookAt(x, y, 0);
		camera.update();
	}
	
	public void setTime(long time) {
		long suneTime = (time + 21600) % 86400;
		
		float sunX = (float) (Math.cos(suneTime * Math.PI * 2 / 86400));
		float sunY = (float) -(Math.sin(suneTime * Math.PI * 2 / 86400));
		
		sun.set(0.8f, 0.8f, 0.8f, sunX, 0.2f, sunY);
	}
	
	public void resize(int width, int height) {
		camera = new PerspectiveCamera(512, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.near = 1f;
		camera.far = 300f;
		setPosition(x, y);
	}
}
