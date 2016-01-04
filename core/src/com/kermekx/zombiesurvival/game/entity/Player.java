package com.kermekx.zombiesurvival.game.entity;

import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.game.inventory.Inventory;
import com.kermekx.zombiesurvival.game.inventory.ItemStack;
import com.kermekx.zombiesurvival.game.item.Item;
import com.kermekx.zombiesurvival.game.item.Item.ItemList;
import com.kermekx.zombiesurvival.game.scene.GameScene;

public class Player extends Entity {

	public static int LIFE = 100;
	public static float MOVEMENT_SPEED = 18f;
	public static float ROTATION_SPEED = 180f;

	private boolean walking = false;
	private boolean run = false;
	private final Inventory inventory = new Inventory(32);

	private float using = 0;

	public Player(GameScene scene, Drawable drawable) {
		super(scene, drawable);
		inventory.addItem(new ItemStack(ItemList.AK47.getItem().getId()));
		inventory.addItem(new ItemStack(ItemList.AMMO.getItem().getId(), 32));
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Entity walk(float delta) {
		if (using > 0)
			return this;

		walking = true;
		return translate(0, delta * MOVEMENT_SPEED, 0);
	}

	@Override
	public Entity rotate(float delta) {
		return super.rotate(delta * ROTATION_SPEED);
	}

	public void use() {
		if (using > 0)
			return;
		Item.items[inventory.getSlot(0).getItemId()].use(this);
	}

	public void fire(float time) {
		using = time;
	}

	public void reload(float time) {
		using = time;
	}

	@Override
	public void update(float delta) {
		if (walking)
			walking = false;
		else if (run)
			run = false;
		else if (using >= 0)
			using -= delta;
		else {
		}
	}
}
