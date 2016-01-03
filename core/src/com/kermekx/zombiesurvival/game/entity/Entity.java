package com.kermekx.zombiesurvival.game.entity;

import com.badlogic.gdx.math.collision.BoundingBox;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.scene.GameScene;

public class Entity {
	
	private final GameScene scene;
	
	protected final Drawable drawable;
	private BoundingBox hitbox = new BoundingBox();
	
	public Entity(GameScene scene, Drawable drawable) {
		this.scene= scene;
		this.drawable = drawable;
		hitbox = drawable.getInstance().calculateBoundingBox(hitbox);
		hitbox.mul(drawable.getInstance().transform);
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public Drawable getDrawable() {
		return drawable;
	}
	
	public BoundingBox getHitbox() {
		return hitbox;
	}
	
	public Entity translate(float x, float y, float z) {
		drawable.getInstance().transform.translate(x, y, z);
		drawable.getInstance().calculateBoundingBox(hitbox);
		hitbox.mul(drawable.getInstance().transform);
		for (Entity entity : scene.getEntities()) {
			if (hitbox.intersects(entity.hitbox) && entity != this) {
				drawable.getInstance().transform.translate(-x, -y, -z);
				drawable.getInstance().calculateBoundingBox(hitbox);
				hitbox.mul(drawable.getInstance().transform);
				return entity;
			}
		}
		
		return null;
	}

}
