package com.riforin.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Abstract class for enemies. 
 * @author William Zhuang
 *
 */
public abstract class Enemy {
	
	public Vector2 position;
	public int tileX;	// x coordinate in tileMap
	public int tileY;	// y coordinate in tileMap
	public int speed;
	public int health;
	
	/** Enemy constructor */
	public Enemy(int tileX0, int tileY0) {
		position = new Vector2(tileX0 * 32, tileY0 * 32);
		tileX = tileX0;
		tileY = tileY0;
	}
	
	/** Enemy update method */
	public void update(float delta) {
		// TODO: Update position, direction, etc. 
	}
}
