package com.kermekx.zombiesurvival.game.entity;

import com.badlogic.gdx.math.Vector3;
import com.kermekx.zombiesurvival.engine.drawable.Box;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.game.inventory.Inventory;
import com.kermekx.zombiesurvival.game.inventory.ItemStack;
import com.kermekx.zombiesurvival.game.item.Item;
import com.kermekx.zombiesurvival.game.item.Item.ItemList;
import com.kermekx.zombiesurvival.game.scene.GameScene;

public class Player extends Entity {

	public static int LIFE = 100;
	public static float MOVEMENT_SPEED = 10f;
	public static float ROTATION_SPEED = 140f;

	private boolean walking = false;
	private boolean run = false;
	private final Inventory inventory = new Inventory(32);

	private Drawable itemInHand;

	private float using = 0;

	public Player(GameScene scene, Drawable drawable) {
		super(scene, drawable);
		inventory.addItem(new ItemStack(ItemList.AK47.getItem().getId()));
		inventory.addItem(new ItemStack(ItemList.AMMO.getItem().getId(), 32));

		setItemInHand(ItemList.AK47.getItem());
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Entity walk(float delta) {
		if (using > 0)
			return this;

		walking = true;
		Entity colide = translate(0, delta * MOVEMENT_SPEED, 0);

		if (colide == null && itemInHand != null) {
			itemInHand.getInstance().transform.translate(delta * MOVEMENT_SPEED, 0, 0);
		}

		return colide;
	}

	@Override
	public Entity rotate(float delta) {
		Entity colide = super.rotate(delta * ROTATION_SPEED);

		if (colide == null && itemInHand != null) {
			itemInHand.getInstance().transform.translate(-0.4f, 0.25f, 0).rotate(0, 0, 1, delta * ROTATION_SPEED)
					.translate(0.4f, -0.25f, 0);
		}

		return colide;
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

	public void setItemInHand(Item item) {
		if (itemInHand != null)
			getScene().removeDrawable(itemInHand);

		if (item == null) {
			itemInHand = null;
			return;
		}

		Vector3 position = getPosition();
		float rotation = getRotation();

		itemInHand = new Box(position.x, position.y, 1.8f, item.getModel().getModel());
		itemInHand.getInstance().transform.rotate(0, 0, 1, rotation).translate(0.4f, -0.25f, 0f);

		getScene().addDrawable(itemInHand);

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
