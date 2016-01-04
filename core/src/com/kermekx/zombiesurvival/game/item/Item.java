package com.kermekx.zombiesurvival.game.item;

import com.kermekx.zombiesurvival.game.entity.Player;

public class Item {
	
	public static Item[] items = new Item[100];
	
	public enum ItemList {
		VOID(new Item(0)),
		AMMO(new Item(1)),
		//KNIFE(new SecondaryWeapon(10)),
		HANDGUN(new Weapon(11, 10)),
		SAWEDOFF(new Weapon(12, 6)),
		AK47(new Weapon(13, 20));
		
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
	
	public Item(int id) {
		this.id = id;
		items[id] = this;
		stackable = true;
	}
	
	public Item(int id, boolean stackable) {
		this.id = id;
		items[id] = this;
		this.stackable = stackable;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isStackable() {
		return stackable;
	}

	public void use(Player player) {
	}

}
