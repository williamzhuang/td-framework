package com.riforin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.riforin.gameobjects.Tile.TILETYPE;
import com.riforin.tdhelpers.AssetLoader;

public class Projectile extends Actor {
	
	Vector2 position;
	Vector2 startPosition;
	Vector2 modPosition;
	Enemy target;
	float speed;
	float alpha;
	Circle boundingCircle;
	
	private float currentTime;
	TextureRegion textureRegion;
	
	
	public Projectile(float xPos, float yPos, Enemy target) {
		this.target = target;
		position = new Vector2(xPos, yPos);
		startPosition = position.cpy();
		modPosition = position.cpy();
		boundingCircle = new Circle();
		// TODO: Modify to accomodate different textures/projectile types.
		textureRegion = AssetLoader.bullet;
		speed = 0.35f;
		
		Gdx.app.log("Projectile", "created");
		boundingCircle.set(position.x + 4, position.y + 4, 4);
		
	}
	
	// Move this towards the target enemy as long as it exists.
	public void act(float delta) {
		// Upon collision.
		if ((Intersector.overlaps(boundingCircle, target.getBoundingCircle())) || (alpha > 1)) {
			this.remove();
			// TODO: Deal damage to the enemy.
		}

		// Calculate the alpha necessary for the movement.
		currentTime += delta;
		alpha = (float) currentTime / speed;

		// Move to the next tile.
		modPosition.x = startPosition.x;
		modPosition.y = startPosition.y;

		modPosition.lerp(target.getCenter(), alpha);
		
		position.x = modPosition.x;
		position.y = modPosition.y;
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(textureRegion, position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
}
