package com.kermekx.zombiesurvival.game.entity;

import com.badlogic.gdx.math.collision.BoundingBox;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.damage.Damage;
import com.kermekx.zombiesurvival.game.damage.Resistance;
import com.kermekx.zombiesurvival.game.scene.GameScene;

public class Entity {
	
	private final GameScene scene;
	
	protected final Drawable drawable;
	private BoundingBox hitbox = new BoundingBox();
	
	private boolean alive = true;
	private int life = 0;
	private final Resistance resistance;
	
	public Entity(GameScene scene, Drawable drawable) {
		this.scene= scene;
		this.drawable = drawable;
		resistance = Resistance.INVISIBLE;
		
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
	
	public void damage(Damage damage) {
		if ((life -= resistance.getDamage(damage)) <= 0)
			kill();
	}

	public void kill() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
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
	
	public Entity rotate(float angle) {
		drawable.getInstance().transform.rotate(0, 0, 1, angle);
		drawable.getInstance().calculateBoundingBox(hitbox);
		hitbox.mul(drawable.getInstance().transform);
		
		for (Entity entity : scene.getEntities()) {
			if (hitbox.intersects(entity.hitbox) && entity != this) {
				drawable.getInstance().transform.rotate(0, 0, 1, -angle);
				drawable.getInstance().calculateBoundingBox(hitbox);
				hitbox.mul(drawable.getInstance().transform);
				return entity;
			}
		}
		
		return null;
	}

}
