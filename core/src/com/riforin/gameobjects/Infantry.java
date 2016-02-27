package com.riforin.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.riforin.tdhelpers.AssetLoader;

public class Infantry extends Tower {

	int moveSpeed;
	
	public Infantry(int tileX0, int tileY0) {
		super(tileX0, tileY0);
		// TODO: Generate a random base character.
		textureRegion = AssetLoader.knight;

	}
	
	@Override
	public void draw(Batch batch, float alpha) {

		batch.draw(textureRegion, position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
}
