package com.riforin.gameobjects;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.riforin.gameobjects.Tile.TILETYPE;
import com.riforin.tdhelpers.AssetLoader;

/**
 * Abstract class for enemies. 
 * @author William Zhuang
 *
 */
public class Enemy extends Actor {
	
	public Vector2 position;
	public int tileX;	// x coordinate in tileMap
	public int tileY;	// y coordinate in tileMap
	public float speed;
	public int health;
	
	// TODO: Add more enemy types.
	public enum ENEMYTYPE {
		flame;
	}
	
	private ENEMYTYPE enemyType;
	private TextureRegion textureRegion;
	private Tile currentTile;
	
	/** Enemy constructor */
	public Enemy(Tile startingTile, ENEMYTYPE enemyType) {
		speed = 1f;
		currentTile = startingTile;
		tileX = startingTile.getTileX();
		tileY = startingTile.getTileY();
		position = new Vector2(tileX * 32, tileY * 32);
		this.enemyType = enemyType;
		
		// TODO: set textures based on what the type is.
		if (enemyType == ENEMYTYPE.flame) {
			textureRegion = AssetLoader.flame;
		}
		
	}
	
	public ENEMYTYPE getEnemyType() {
		return enemyType;
	}
	
	public void setX(float x) {
		position.x = x;
	}
	
	public void setY(float y) {
		position.y = y;
	}
	
	public float getX(float x) {
		return position.x;
	}
	
	public float getY(float y) {
		return position.y;
	}
	
	public void move() {
		// Tween between the current position and the center of the next tile.
		// Once the next tile's center has been reached, change the current Tile and 
		// tween to the next tile until the end is reached.
		// At the end, destroy itself. 
		if (currentTile.getType() == TILETYPE.end) {
			this.remove();
		}
		
		if ((currentTile.getX() == position.x) && (currentTile.getY() == position.y)) {
			currentTile = currentTile.getNextTile();
		}
		
		Tween.to(this, EnemyTween.xPOS, speed).target(currentTile.getX());
	}
}
