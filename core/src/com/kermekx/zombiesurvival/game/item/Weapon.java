package com.kermekx.zombiesurvival.game.item;

import com.badlogic.gdx.math.Vector3;
import com.kermekx.zombiesurvival.engine.drawable.Box;
import com.kermekx.zombiesurvival.engine.drawable.Drawable;
import com.kermekx.zombiesurvival.game.entity.Bullet;
import com.kermekx.zombiesurvival.game.entity.Player;
import com.kermekx.zombiesurvival.game.inventory.ItemStack;
import com.kermekx.zombiesurvival.game.model.GameModel;

public class Weapon extends Item {

	private final int charger;
	private int countShot = 0;

	public Weapon(int id, GameModel model, int charger) {
		super(id, model, false);
		this.charger = charger;
	}

	@Override
	public void use(Player player) {
		if (countShot < charger) {
			if (player.getInventory().removeItem(new ItemStack(ItemList.AMMO.getItem().getId())) == null) {
				player.fire(0.15f);
				
				Vector3 position = player.getPosition();
				float rotation = player.getRotation();
				
				Drawable d = new Box(position.x, position.y, 1.8f, GameModel.BULLET.getModel());
				d.getInstance().transform.rotate(0, 0, 1, rotation).translate(0.6f, -0.32f, 0f);
				player.getGameScene().addEntity(new Bullet(player.getGameScene(), d, 20));
				
				//Sounds.GUNSHOT.play(new Vector(0, 0));
				countShot++;
			} else {
				//Sounds.TRIGGER.play();
			}

		} else {
			player.reload(0.6f);
			countShot = 0;
			//Sounds.TRIGGER.play();
		}
	}

	public int getCharger() {
		return charger;
	}

	public void setCountShot(int countShot) {
		this.countShot = countShot;
	}

}
