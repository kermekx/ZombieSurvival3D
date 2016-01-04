package com.kermekx.zombiesurvival.game.ai;

import com.kermekx.zombiesurvival.game.entity.Entity;

public class DropOnDeath extends BaseAI {

	private Entity entity;
	private final int texture;
	//private final Vector size;

	public DropOnDeath(Entity entity, int texture) {
		super(entity);
		this.texture = texture;
		this.entity = entity;
		//this.size = entity.getHitbox().getSize();
	}
	
	public DropOnDeath(Entity entity, int texture, Vector size) {
		super(entity);
		this.texture = texture;
		this.entity = entity;
		//this.size = size;
	}

	@Override
	public void update(int delta) {
		if (!entity.isAlive()) {
			GameScene context = (GameScene) entity.getContext();
			context.addEntity(new DeathEntity(context, entity.getPosition(), size, entity.getRotation(),
					texture));
		}
	}
}
