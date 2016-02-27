package com.riforin.gameobjects;
/**
 * Standard class for ranged turrets.
 * @author William Zhuang
 * 
 */
public class RangedTower extends Tower {

	public RangedTower(int x0, int y0) {
		super(x0, y0);
		range = 3;
		damage = 1;
		atkSpeed = 30;
	}

}
