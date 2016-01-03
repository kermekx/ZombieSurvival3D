package com.kermekx.zombiesurvival.game.entity;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.damage.Damage;
import com.kermekx.zombiesurvival.game.damage.Resistance;
import com.kermekx.zombiesurvival.game.scene.GameScene;

public abstract class Entity {

	private final GameScene scene;

	protected final Drawable drawable;
	private BoundingBox hitbox = new BoundingBox();

	private boolean alive = true;
	private int life = 1;
	private final Resistance resistance;

	private float rotation = 90;

	public Entity(GameScene scene, Drawable drawable) {
		this.scene = scene;
		this.drawable = drawable;
		resistance = Resistance.INVINSIBLE;

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

				ModelInstance model = getDrawable().getInstance().copy();
				ModelInstance model1 = entity.getDrawable().getInstance().copy();

				model.transform.rotate(0, 0, 1, -rotation);

				Vector3 translate = new Vector3();
				model.transform.getTranslation(translate);
				Vector3 translate1 = new Vector3();
				model1.transform.getTranslation(translate1);

				model1.transform.setTranslation(translate);
				model1.transform.rotate(0, 0, 1, -rotation);
				model1.transform.translate(new Vector3(translate1.x - translate.x, translate1.y - translate.y, 0));

				BoundingBox hb = new BoundingBox();
				model.calculateBoundingBox(hb);
				hb.mul(model.transform);
				BoundingBox hb1 = new BoundingBox();
				model1.calculateBoundingBox(hb1);
				hb1.mul(model1.transform);

				if (hb.intersects(hb1)) {
					drawable.getInstance().transform.translate(-x, -y, -z);
					drawable.getInstance().calculateBoundingBox(hitbox);
					hitbox.mul(drawable.getInstance().transform);
					return entity;
				}
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

				ModelInstance model = getDrawable().getInstance().copy();
				ModelInstance model1 = entity.getDrawable().getInstance().copy();

				model.transform.rotate(0, 0, 1, -rotation);

				Vector3 translate = new Vector3();
				model.transform.getTranslation(translate);
				Vector3 translate1 = new Vector3();
				model1.transform.getTranslation(translate1);

				model1.transform.setTranslation(translate);
				model1.transform.rotate(0, 0, 1, -rotation);
				model1.transform.translate(new Vector3(translate1.x - translate.x, translate1.y - translate.y, 0));

				BoundingBox hb = new BoundingBox();
				model.calculateBoundingBox(hb);
				hb.mul(model.transform);
				BoundingBox hb1 = new BoundingBox();
				model1.calculateBoundingBox(hb1);
				hb1.mul(model1.transform);

				if (hb.intersects(hb1)) {
					drawable.getInstance().transform.rotate(0, 0, 1, -angle);
					drawable.getInstance().calculateBoundingBox(hitbox);
					hitbox.mul(drawable.getInstance().transform);
					return entity;
				}
			}
		}

		rotation += angle;

		return null;
	}

	public float getRotation() {
		return rotation;
	}

	public abstract void update(float delta);
}
