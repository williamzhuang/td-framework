package com.riforin.gameobjects;

/**
 * Abstract class for enemies. 
 * @author William Zhuang
 *
 */
public abstract class Enemy {
	
	public int x;		// x location to draw to
	public int y;		// y location to draw to.
	public int tileX;	// x coordinate in tileMap
	public int tileY;	// y coordinate in tileMap
	public int speed;
	public int health;
	
	/** Enemy constructor */
	public Enemy(int tileX0, int tileY0) {
		x = tileX0 * 40;
		y = tileY0 * 40;
		tileX = tileX0;
		tileY = tileY0;
	}
	
	/** Enemy update method */
	public void update(float delta) {
		// TODO: Update position, direction, etc. 
	}
}
