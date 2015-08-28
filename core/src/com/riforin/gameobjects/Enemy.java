package com.riforin.gameobjects;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Elastic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
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
	private Tile nextTile;
	private TweenManager tweenManager;
	private boolean spawned;
	private Tween tempTween;
	private TILETYPE tileType;
	
	/** Enemy constructor */
	public Enemy(Tile startingTile, ENEMYTYPE enemyType, TweenManager tweenManager) {
		speed = 0.65f;
		nextTile = startingTile.getNextTile();
		tileType= nextTile.getType();
		
		tileX = startingTile.getTileX();
		tileY = startingTile.getTileY();
		position = new Vector2(tileX * 32, tileY * 32);
		this.enemyType = enemyType;
		
		// TODO: set textures based on what the type is.
		if (enemyType == ENEMYTYPE.flame) {
			textureRegion = AssetLoader.flame;
		}
		spawned = false;
		this.tweenManager = tweenManager;
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
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public void act(float delta) {
		// Tween between the current position and the center of the next tile.
		// Once the next tile's center has been reached, change the current Tile and 
		// tween to the next tile until the end is reached.
		// At the end, destroy itself. 
		
		if (nextTile.getType() == TILETYPE.end) {
			this.remove();
		}
		
		if ((Math.abs(nextTile.getX() - position.x) < 0.001) && (Math.abs(nextTile.getY() - position.y) < 0.001)) {
			nextTile = nextTile.getNextTile();
			tempTween = Tween.to(this, EnemyAccessor.POSITION_XY, speed).target(nextTile.getX(), nextTile.getY())
					.ease(Elastic.INOUT)
					.start(tweenManager);
		}
		
		if (!spawned) {
			spawned = true;
			tempTween = Tween.to(this, EnemyAccessor.POSITION_XY, speed).target(nextTile.getX(), nextTile.getY())
					.ease(Elastic.INOUT)
					.start(tweenManager);
		}
		
		// Move to the next tile.
		Gdx.app.log("This", position.x + " " + position.y);
	}
	
	// Moves this enemy unit from tile to tile and also draws this stuff.
	public void draw(Batch batch, float alpha) {
		batch.draw(textureRegion, position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
}
