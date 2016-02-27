package com.riforin.gameobjects;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.riforin.gameobjects.Tile.TILETYPE;
import com.riforin.tdhelpers.AssetLoader;

/**
 * Abstract class for enemies.
 * 
 * @author William Zhuang
 * 
 */
public class Enemy extends Actor {

	public Vector2 position;			// Actual position of the enemy.
	private Vector2 modPosition;		// Vector used for interpolation between current and next position.
	private Vector2 lastPosition;		// Stores the last visited tile position
	public int tileX; // x coordinate in tileMap
	public int tileY; // y coordinate in tileMap
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
	private float alpha;
	private float currentTime;
	private EnemyHandler handler;

	private Circle boundingCircle;

	/** Enemy constructor */
	public Enemy(Tile startingTile, ENEMYTYPE enemyType,
			TweenManager tweenManager, EnemyHandler handler) {
		speed = 0.35f;
		nextTile = startingTile.getNextTile();
		tileType = nextTile.getType();

		tileX = startingTile.getTileX();
		tileY = startingTile.getTileY();
		position = new Vector2(tileX * 32, tileY * 32);
		lastPosition = position.cpy();
		modPosition = position.cpy();
		this.handler = handler;
		this.enemyType = enemyType;

		// TODO: set textures based on what the type is.
		if (enemyType == ENEMYTYPE.flame) {
			textureRegion = AssetLoader.flame;
		}
		spawned = true;
		this.tweenManager = tweenManager;

		boundingCircle = new Circle();
		
	}

	public void act(float delta) {
		// Tween between the current position and the center of the next tile.
		// Once the next tile's center has been reached, change the current Tile
		// and
		// tween to the next tile until the end is reached.
		// At the end, destroy itself.
		// TODO: Alter hitbox based on size.
		boundingCircle.set(position.x + 16, position.y + 16, 32);
				
		if ((Math.abs(nextTile.getX() - position.x) < 25)
				&& (Math.abs(nextTile.getY() - position.y) < 25)) {
			currentTime = 0;
			lastPosition = position.cpy();
			nextTile = nextTile.getNextTile();
		}

		if (nextTile.getType() == TILETYPE.end) {
			// TODO: Death animation.
			spawned = false;
			handler.getEnemyList().remove(this);
			this.remove();
		}

		// Calculate the alpha necessary for the movement.
		currentTime += delta;
		alpha = (float) currentTime / speed;

		// Move to the next tile.
		modPosition = lastPosition.cpy();

		modPosition.lerp(nextTile.getPosition(), alpha);
		
		position = modPosition.cpy();
	
	}

	// Moves this enemy unit from tile to tile and also draws this stuff.
	public void draw(Batch batch, float alpha) {
		batch.draw(textureRegion, position.x, position.y,
				textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
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
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getCenter() {
		return new Vector2(position.x + 16, position.y + 16);
	}
	
	public boolean getSpawned() {
		return spawned;
	}

}
