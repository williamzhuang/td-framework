package com.riforin.gameobjects;
/**
 * Standard class for ranged turrets.
 * @author William Zhuang
 * 
 */
public class RangedTurret extends Turret {

	public RangedTurret(int x0, int y0) {
		super(x0, y0);
		range = 3;
		damage = 1;
		atkSpeed = 3;
	}

	public void update(float delta) {
		if (timer == 0) {
			attack();
			timer = atkSpeed + 1;
		}
		
		timer -= 1;
	}
	
	public void attack() {
		// TODO: Find nearest enemy in range and attack. 
	}

	public void givePart(int range, int damage, int atkSpeed) {

	}

}
