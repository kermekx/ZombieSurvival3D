package com.kermekx.zombiesurvival.game.item;

import com.kermekx.zombiesurvival.game.entity.Player;
import com.kermekx.zombiesurvival.game.model.GameModel;

public class Item {
	
	public static Item[] items = new Item[100];
	
	public enum ItemList {
		VOID(new Item(0, null)),
		AMMO(new Item(1, null)),
		//KNIFE(new SecondaryWeapon(10)),
		HANDGUN(new Weapon(11, GameModel.SMITH_AND_WESSON, 10)),
		SAWEDOFF(new Weapon(12, GameModel.AK47, 6)),
		AK47(new Weapon(13, GameModel.AK47, 20));
		
		private final Item item;
		
		ItemList(Item item) {
			this.item = item;
		}
		
		public Item getItem() {
			return item;
		}
	}
	
	private final int id;
	private final boolean stackable;
	
	private final GameModel model;
	
	public Item(int id, GameModel model) {
		this.id = id;
		this.model = model;
		items[id] = this;
		stackable = true;
	}
	
	public Item(int id, GameModel model, boolean stackable) {
		this.id = id;
		this.model = model;
		items[id] = this;
		this.stackable = stackable;
	}
	
	public int getId() {
		return id;
	}
	
	public GameModel getModel() {
		return model;
	}
	
	public boolean isStackable() {
		return stackable;
	}

	public void use(Player player) {
	}

}
