package com.riforin.gameobjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.riforin.tdhelpers.AssetLoader;

/**
 * Abstract class for turrets.
 * @author William Zhuang
 *
 */
public class Tower extends Actor {
	
	Vector2 position;
	int tileX;		// TileMap x location.
	int tileY;		// TileMap y location.
	int damage;
	int range;
	int atkSpeed;
	int timer; 		// Time between attacks. Will count down until 0 and attack and reset.
	
	TextureRegion textureRegion;
	
	/** Standard turret constructor. Converts tile coordinates 
	 * into draw coordinates. */
	public Tower(int tileX0, int tileY0) {
		// TODO: Make and assign a tower texture.
		textureRegion = AssetLoader.tower;
		this.setVisible(true);
		damage = 1;
		range = 3;
		atkSpeed = 3;
				
		position = new Vector2(tileX0 * 32, tileY0 * 32);
		tileX = tileX0;
		tileY = tileY0;
		timer = 0;
	}
	
	public void attack() {
		
	}
	
	public void draw(Batch batch, float alpha) {
		
		batch.draw(textureRegion, position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
	
}
