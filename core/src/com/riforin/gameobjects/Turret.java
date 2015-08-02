package com.riforin.gameobjects;

/**
 * Abstract class for turrets.
 * @author William Zhuang
 *
 */
public abstract class Turret {
	
	int x;			// x location to draw to. 
	int y;			// y location to draw to. 
	int tileX;		// TileMap x location.
	int tileY;		// TileMap y location.
	int damage;		 
	int range;		 
	int atkSpeed;	
	int timer; 		// Time between attacks. Will count down until 0 and attack and reset.
	
	/** Standard turret constructor. Converts tile coordinates 
	 * into draw coordinates. */
	public Turret(int tileX0, int tileY0) {
		x = tileX0 * 40;
		y = tileY0 * 40;
		tileX = tileX0;
		tileY = tileY0;
		timer = 0;
	}
	
	public void update(float delta) {
		
	}
	
	public void attack() {
		
	}
	
}
