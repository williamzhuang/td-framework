package com.riforin.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Abstract class for turrets.
 * @author William Zhuang
 *
 */
public abstract class Turret {
	
	Vector2 position;
	int tileX;		// TileMap x location.
	int tileY;		// TileMap y location.
	int damage;		 
	int range;		 
	int atkSpeed;	
	int timer; 		// Time between attacks. Will count down until 0 and attack and reset.
	
	/** Standard turret constructor. Converts tile coordinates 
	 * into draw coordinates. */
	public Turret(int tileX0, int tileY0) {
		position = new Vector2(tileX0 * 32, tileY0 * 32);
		tileX = tileX0;
		tileY = tileY0;
		timer = 0;
	}
	
	public void update(float delta) {
		
	}
	
	public void attack() {
		
	}
	
}
