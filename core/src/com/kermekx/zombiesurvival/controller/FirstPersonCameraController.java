package com.kermekx.zombiesurvival.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;
import com.kermekx.zombiesurvival.engine.camera.Camera;
import com.kermekx.zombiesurvival.game.entity.Player;
import com.kermekx.zombiesurvival.game.scene.GameScene;

public class FirstPersonCameraController extends InputAdapter {
	
	private final GameScene scene;
	private final Camera camera;
	private final IntIntMap keys = new IntIntMap();
	private int STRAFE_LEFT = Keys.Q;
	private int STRAFE_RIGHT = Keys.D;
	private int FORWARD = Keys.Z;
	private int BACKWARD = Keys.S;
	private float sensibility = 0.5f;
	private final Vector3 tmp = new Vector3();
	
	public FirstPersonCameraController(GameScene scene) {
		this.scene = scene;
		camera = scene.getCamera();
	}
	
	@Override
	public boolean keyDown (int keycode) {
		keys.put(keycode, keycode);
		return true;
	}

	@Override
	public boolean keyUp (int keycode) {
		keys.remove(keycode, 0);
		return true;
	}
	
	public void setSensibility (float sensibility) {
		this.sensibility = sensibility;
	}
	
	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		float deltaX = -Gdx.input.getDeltaX() * sensibility;
		float deltaY = -Gdx.input.getDeltaY() * sensibility;
		if (scene.getPlayer().rotate(deltaX) == null)
			camera.rotate(deltaX);
		tmp.set(camera.getCamera().direction).crs(camera.getCamera().up).nor();
		camera.getCamera().direction.rotate(tmp, deltaY);
		return true;
	}

	public void update () {
		update(Gdx.graphics.getDeltaTime());
	}
	
	public void update (float deltaTime) {

		Player player = scene.getPlayer();
		
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			scene.getPlayer().use();
		}
		
		if (keys.containsKey(FORWARD)) {
			if (player.walk(deltaTime) == null)
				camera.translate(0, deltaTime * Player.MOVEMENT_SPEED);
		}
		
		if (keys.containsKey(BACKWARD)) {
			if (player.walk(-deltaTime) == null)
				camera.translate(0, -deltaTime * Player.MOVEMENT_SPEED);
		}
		
		if (keys.containsKey(STRAFE_LEFT)) {
			if (player.strafe(-deltaTime) == null)
				camera.translate(-deltaTime * Player.MOVEMENT_SPEED, 0);
		}
		
		if (keys.containsKey(STRAFE_RIGHT)) {
			if (player.strafe(deltaTime) == null)
				camera.translate(deltaTime * Player.MOVEMENT_SPEED, 0);
		}
		
		/**
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
			if (player.rotate(delta) == null)
				getCamera().rotate(delta * Player.ROTATION_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (player.rotate(-delta) == null)
				getCamera().rotate(-delta * Player.ROTATION_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
			if (player.walk(delta) == null)
				getCamera().translate(0, delta * Player.MOVEMENT_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (player.walk(-delta) == null)
				getCamera().translate(0, -delta * Player.MOVEMENT_SPEED);
		}
		*/
		
		camera.getCamera().update(true);
	}
}
