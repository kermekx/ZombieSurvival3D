package com.kermekx.zombiesurvival.game.entity;

import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.game.damage.Damage;
import com.kermekx.zombiesurvival.game.damage.DamageSource;
import com.kermekx.zombiesurvival.game.scene.GameScene;

public class Bullet extends Entity {

	private final int damage;
	private int lifeTime = 0;
	
	public Bullet(GameScene scene, Drawable drawable, int damage) {
		super(scene, drawable);
		this.damage = damage;
	}

	@Override
	public void update(float delta) {
		lifeTime += delta;
		if (lifeTime > 5000)
			kill();
		
		Entity target = translate(delta * 50, 0f, 0f);
		if (target != null) {
			target.damage(new Damage(this, DamageSource.BULLET, damage));
			this.kill();
		}
	}

}
