package com.riforin.tdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static TextureRegion hero; 
	public static TextureRegion obstacle;
	
	public static void load() {
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		hero = new TextureRegion(texture, 0, 0, 22, 22);
		hero.flip(false, true);
		
		obstacle = new TextureRegion(texture, 0, 22, 120, 10);
		obstacle.flip(false, true);	
	}
	
	public static void dispose() {
		texture.dispose();
	}

}
