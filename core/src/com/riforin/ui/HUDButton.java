package com.riforin.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.riforin.gameobjects.EnemyHandler;
import com.riforin.gameobjects.RangedTower;
import com.riforin.tdhelpers.AssetLoader;

public class HUDButton extends Actor {
	
	Vector2 position;
	
	public enum HUDBUTTONTYPE {
		nextWave;
	}
	
	HUDBUTTONTYPE buttonType;
	TextureRegion textureRegion;
	
	public HUDButton(HUDBUTTONTYPE buttonType, final EnemyHandler enemyHandler) {
		position = new Vector2();
		// TODO: Make a graphic for when the touch is released.

		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("HUDButton", "Pressed");
				enemyHandler.spawn();
				return true;
			}
		});
		
		if (buttonType == HUDBUTTONTYPE.nextWave) {
			position.x = 672;
			position.y = 0;
			textureRegion = AssetLoader.nextWave;
			
		}
		
		setBounds(position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(textureRegion, position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
}
