package com.riforin.tdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static TextureRegion selector;
	public static TextureRegion destroy;
	public static TextureRegion newtower;
	public static TextureRegion newinfantry;
	public static TextureRegion upgrade;
	public static TextureRegion wheel;
	
	public static void load() {
		texture = new Texture(Gdx.files.internal("UI/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		selector = new TextureRegion(texture, 0, 64, 32, 32);
		
		// Loading menu icons.
		destroy = new TextureRegion(texture, 0, 0, 64, 64);
		newinfantry = new TextureRegion(texture, 64, 0, 64, 64);
		newtower = new TextureRegion(texture, 128, 0, 64, 64);
		upgrade = new TextureRegion(texture, 192, 0, 64, 64);
		wheel = new TextureRegion(texture, 0, 96, 160, 160);
	}
	
	public static void dispose() {
		texture.dispose();
	}

}
