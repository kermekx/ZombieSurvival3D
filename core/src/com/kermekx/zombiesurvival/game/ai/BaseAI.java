package com.kermekx.zombiesurvival.game.ai;

import com.kermekx.zombiesurvival.game.entity.Entity;

public abstract class BaseAI implements AI {
	
	protected final Entity entity;
	
	public BaseAI(Entity entity) {
		this.entity = entity;
	}

}
