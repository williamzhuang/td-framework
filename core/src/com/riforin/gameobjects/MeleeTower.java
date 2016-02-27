package com.riforin.gameobjects;

/** 
 * Standard class for melee range turrets.
 * @author William Zhuang
 */
public class MeleeTower extends Tower {
	
	/** Melee turret constructor. */
	public MeleeTower(int tileX0, int tileY0) {
		super(tileX0, tileY0);
		range = 2;
		damage = 2;
		atkSpeed = 2;
	}
	
	/** 
	 * Updates turret. 
	 * @param delta Float synchronizing time delay. 
	 */
	public void update(float delta) {
		if (timer == 0) {
			attack();
			timer = atkSpeed + 1;
		}
		
		timer -= 1;
	}	
	
	/**
	 * Updates the turrets properties. 
	 * @param range Range to be added/removed. 
	 * @param damage Damage to be added/removed.
	 * @param atkSpeed Attack Speed to be added/removed.
	 */
	public void givePart(int range, int damage, int atkSpeed) {
		
	}
}
