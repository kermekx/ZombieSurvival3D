package com.kermekx.zombiesurvival.engine.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;

public class Camera {

	private PerspectiveCamera camera;
	private final Environment environment;
	private DirectionalLight sun = new DirectionalLight();

	private float x = 0, y = 0, angle = 90;

	public Camera() {
		camera = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.near = 1f;
		camera.far = 1000F;
		setPosition(x, y);

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(sun);

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
		this.angle = 90;
		camera.position.set(x, y - 126f, 512f);
		camera.lookAt(x, y, 0);
		camera.update();
	}

	public void translate(float x, float y) {
		double radian = Math.toRadians(angle);
		float tx = (float) (x * Math.sin(radian) + y * Math.cos(radian));
		float ty = (float) (y * Math.sin(radian) + x * Math.cos(radian));
		this.x += tx;
		this.y += ty;
		
		camera.translate(tx, ty, 0);
		camera.update();
	}

	public void setRotation(float angle) {
		rotate(angle - this.angle);
	}

	public void rotate(float angle) {
		this.angle += angle;
		camera.rotateAround(new Vector3(x, y, 0), new Vector3(0, 0, 1), angle);
		camera.update();
	}

	public void setTime(long time) {
		long suneTime = (time + 21600) % 86400;

		float sunX = (float) (Math.cos(suneTime * Math.PI * 2 / 86400));
		float sunY = (float) (Math.sin(suneTime * Math.PI * 2 / 86400));

		sun.set(0.8f, 0.8f, 0.8f, sunX, 0.2f, sunY);
	}

	public void resize(int width, int height) {
		camera = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.near = 1f;
		camera.far = 1000f;
		setPosition(x, y);
	}
}
