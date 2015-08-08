package com.riforin.towerdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.riforin.screens.GameScreen;
import com.riforin.tdhelpers.AssetLoader;

public class towerdefense extends Game {

	@Override
	public void create () {
		Gdx.app.log("towerdefense", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
