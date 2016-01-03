package com.kermekx.zombiesurvival.game.damage;

public enum Resistance {
	
	INVISIBLE(0), NORMAL(1);
	
	private final float resistance;
	
	private Resistance(float resistance) {
		this.resistance = resistance;
	}
	
	public float getDamage(Damage damage) {
		return damage.getDamage() * resistance;
	}

}
