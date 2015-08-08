package com.riforin.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.riforin.gameobjects.Tile;
import com.riforin.tdhelpers.AssetLoader;

public class Button extends Actor{
	
	Vector2 position;
	Tile affectedTile;
	
	TextureRegion textureRegion;
	
	public enum BUTTONTYPE {
		tower, infantry, upgrade, destroy, wheel
	}
	
	private BUTTONTYPE type;
	private UIManager manager;
	
	public Button(BUTTONTYPE type, UIManager manager) {
		position = new Vector2(0, 0);
		this.type = type;
		
		if (type == BUTTONTYPE.tower) {
			textureRegion = AssetLoader.newtower;
		}
		
		else if (type == BUTTONTYPE.infantry) {
			textureRegion = AssetLoader.newinfantry;
		}
		
		else if (type == BUTTONTYPE.upgrade) {
			textureRegion = AssetLoader.upgrade;
		}
		
		else if (type == BUTTONTYPE.destroy) {
			textureRegion = AssetLoader.destroy;
		}
		
		else if (type == BUTTONTYPE.wheel){
			setOrigin(40, 40);
			textureRegion = AssetLoader.wheel;
		}
		
		
		
		this.manager = manager;
	}
	
	public void show(Tile tile) {
		this.affectedTile = tile;
		
		if (type == BUTTONTYPE.wheel) {
			position.x = affectedTile.getPosition().x - 64;
			position.y = affectedTile.getPosition().y - 64; 
		} else if ((type == BUTTONTYPE.infantry) || (type == BUTTONTYPE.upgrade)) {
			position.x = affectedTile.getPosition().x - 96;
			position.y = affectedTile.getPosition().y - 16;
		} else {
			position.x = affectedTile.getPosition().x + 64;
			position.y = affectedTile.getPosition().y - 16;
		}
		
		setBounds(position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
		
		this.setVisible(true);
	}
	
	@Override 
	public void draw(Batch batch, float alpha) {
		
		if (type == BUTTONTYPE.wheel) {
			batch.draw(textureRegion, position.x, position.y, 80, 80, 160, 160, 1, 1, getRotation());
		} else {
			batch.draw(textureRegion, position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
		}
	}
	
	@Override
	public void act(float delta) {
		if (type == BUTTONTYPE.wheel) {
			rotateBy((float) 0.5);
		}
	}
}
