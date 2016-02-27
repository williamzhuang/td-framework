package com.riforin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.riforin.tdhelpers.AssetLoader;

/**
 * Abstract class for turrets.
 * 
 * @author William Zhuang
 * 
 */
public class Tower extends Actor {

	Vector2 position;
	int tileX; // TileMap x location.
	int tileY; // TileMap y location.
	int damage;
	int range;
	float atkSpeed; // Higher numbers are slower attack speeds
	float timer; // Frames left until the next attack.

	private Circle rangeCircle;
	protected TextureRegion textureRegion;
	private Enemy target;

	/**
	 * Standard turret constructor. Converts tile coordinates into draw
	 * coordinates.
	 */
	public Tower(int tileX0, int tileY0) {
		// TODO: Make and assign a tower texture.
		textureRegion = AssetLoader.tower;
		this.setVisible(true);
		damage = 1;
		range = 4;
		atkSpeed = 0.25f;

		position = new Vector2(tileX0 * 32, tileY0 * 32);
		tileX = tileX0;
		tileY = tileY0;
		timer = 0;

		rangeCircle = new Circle();

		// The range circle is centered in the tile of the tower and has a
		// default range of 4 tiles.
		rangeCircle.set(position.x + textureRegion.getRegionWidth() / 2,
				position.y + textureRegion.getRegionHeight() / 2, range * 32);
		target = null;
	}

	/**
	 * Called when a target is specified. Creates a projectile targeted at the
	 * enemy's position. This will be default behavior and can be overrided by
	 * inheriting classes.
	 **/
	public void attack() {
		getParent().addActor(
				new Projectile(position.x + textureRegion.getRegionWidth() / 2,
						position.y + textureRegion.getRegionHeight() / 2,
						target));
	}

	public void act(float delta) {
		if (target != null) {
			// Attack the enemy and reset the countdown timer.
			if (timer <= 0) {
				attack();
				timer = atkSpeed;
			}

			// If the enemy moves out of range.
			if ((!Intersector.overlaps(target.getBoundingCircle(), rangeCircle)) || (!target.getSpawned())) {
				target = null;
			}

			timer -= delta * atkSpeed;
		} else {
			// Remove the number of seconds since the last render call.
			timer -= delta * atkSpeed;
		}

	}

	public void setTarget(Enemy target) {
		this.target = target;
	}

	public Circle getRangeCircle() {
		return rangeCircle;
	}

	public Enemy getTarget() {
		return target;
	}

	public void draw(Batch batch, float alpha) {

		batch.draw(textureRegion, position.x, position.y,
				textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}

}
