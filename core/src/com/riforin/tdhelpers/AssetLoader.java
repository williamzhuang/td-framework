package com.riforin.tdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	// UI Textures
	public static Texture texture;
	public static TextureRegion selector;
	public static TextureRegion destroy;
	public static TextureRegion newtower;
	public static TextureRegion newinfantry;
	public static TextureRegion upgrade;
	public static TextureRegion wheel;
	public static TextureRegion nextWave;
	
	
	// Tower textures
	public static TextureRegion knight;
	public static TextureRegion tower;
	
	// Enemy textures
	public static TextureRegion flame;
	
	
	public static void load() {
		texture = new Texture(Gdx.files.internal("texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		selector = new TextureRegion(texture, 0, 64, 32, 32);
		
		// Loading menu icons and assets.
		destroy = new TextureRegion(texture, 0, 0, 64, 64);
		newinfantry = new TextureRegion(texture, 64, 0, 64, 64);
		newtower = new TextureRegion(texture, 128, 0, 64, 64);
		upgrade = new TextureRegion(texture, 192, 0, 64, 64);
		wheel = new TextureRegion(texture, 0, 96, 160, 160);
		nextWave = new TextureRegion(texture, 352, 0, 128, 64);
		
		// Loading unit textures. 
		knight = new TextureRegion(texture, 256, 0, 32, 64);
		tower = new TextureRegion(texture, 288, 0, 32, 64);
		flame = new TextureRegion(texture, 320, 0, 32, 64);
	}
	
	public static void dispose() {
		texture.dispose();
	}

}
