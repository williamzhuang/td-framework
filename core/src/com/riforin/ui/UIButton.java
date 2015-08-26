package com.riforin.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.riforin.gameobjects.Infantry;
import com.riforin.gameobjects.RangedTower;
import com.riforin.gameobjects.Tile;
import com.riforin.gameobjects.Tile.TILETYPE;
import com.riforin.gameobjects.Tower;
import com.riforin.tdhelpers.AssetLoader;

public class UIButton extends Actor{
	
	Vector2 position;
	Tile affectedTile;
	
	TextureRegion textureRegion;
	
	public enum BUTTONTYPE {
		tower, infantry, upgrade, destroy, wheel;
	}
	
	private BUTTONTYPE type;
	private final UIManager manager;
	
	public UIButton(BUTTONTYPE type, final UIManager manager) {
		position = new Vector2(0, 0);
		this.type = type;
		
		if (type == BUTTONTYPE.tower) {
			textureRegion = AssetLoader.newtower;
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					RangedTower toPlace = new RangedTower(Math.round(affectedTile.getTileX()), Math.round(affectedTile.getTileY()));
					affectedTile.occupy(toPlace);
					manager.createTower(toPlace);
					manager.closeAll();
					return true;
				}
			});
		}
		
		else if (type == BUTTONTYPE.infantry) {
			textureRegion = AssetLoader.newinfantry;
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					
					Infantry toPlace = new Infantry(Math.round(affectedTile.getTileX()), Math.round(affectedTile.getTileY()));
					affectedTile.occupy(toPlace);
					manager.createInfantry(toPlace);
					manager.closeAll();
					return true;
				}
			});
		}
		
		else if (type == BUTTONTYPE.upgrade) {
			// TODO: Open a list of selectable parts to upgrade.
			// Upon part selection, apply the upgrade to the tower and remove the part from the inventory. 
			textureRegion = AssetLoader.upgrade;
		}
		
		else if (type == BUTTONTYPE.destroy) {
			textureRegion = AssetLoader.destroy;
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					// TODO: Give parts back?
					Tower removedTower = affectedTile.removetower();
					removedTower.remove();
					manager.closeAll();
					return true;
				}
			});
		}
		
		else if (type == BUTTONTYPE.wheel) {
			setOrigin(40, 40);
			textureRegion = AssetLoader.wheel;
			setTouchable(Touchable.disabled);
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
