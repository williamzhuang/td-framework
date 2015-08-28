package com.riforin.gameobjects;

import java.util.ArrayList;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.riforin.gameobjects.Enemy;
import com.riforin.gameobjects.Enemy.ENEMYTYPE;

/**
 * Handles spawning and behavior of all enemies.
 * 
 * @author William Zhuang
 * 
 */
public class EnemyHandler extends Actor {

	Tile startingTile;
	TileMap tileMap;
	int startTileX;
	int startTileY;
	Group enemyGroup;
	int waveNumber;

	ArrayList<Wave> waveList;
	TweenManager tweenManager;

	// TODO: Some UI Buttons that start the wave.
	/** EnemyHandler constructor */
	public EnemyHandler(TileMap tileMap, int mapNumber, Tile startingTile,
			Group enemyGroup, TweenManager tweenManager) {
		this.tileMap = tileMap;
		this.startingTile = startingTile;
		startTileX = startingTile.getTileX();
		startTileY = startingTile.getTileY();
		this.tweenManager = tweenManager;

		waveList = new ArrayList<Wave>();
		this.enemyGroup = enemyGroup;
		
		waveNumber = 0;
		
		loadWaves(mapNumber);
	}

	// TODO: Load waves from a text file or something like that.
	private void loadWaves(int mapNumber) {
		if (mapNumber == 0) {
			// TODO: make this specific to the map number.
			ArrayList<ENEMYTYPE> enemyTypes = new ArrayList<ENEMYTYPE>();
			enemyTypes.add(ENEMYTYPE.flame);
			
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			numbers.add(1);
			Wave wave0 = new Wave(0, enemyTypes, numbers);
			
			waveList.add(wave0);
		}
	}

	// Spawns enemies
	// Have a button from the UI that starts the wave.
	// Calls draw methods and puts enemy actors on the screen.
	public void spawn() {
		
		Wave wave = waveList.get(waveNumber);
		// TODO: Add a delay between waves / wait for input
		ArrayList<ENEMYTYPE> enemyTypes = wave.getEnemies();
		ArrayList<Integer> numbers = wave.getNumbers();
		int counter = 0;
		
		for (ENEMYTYPE enemyType : enemyTypes) {
			singleSummon(enemyType, numbers.get(counter));
			counter += 1;
		}
	}

	// Summons a single type of enemy a single number of times
	public void singleSummon(ENEMYTYPE enemyType, int number) {
		// TODO: Delay between summons.
		for (int i = 0; i < number; i += 1) {
			Enemy tempEnemy = new Enemy(startingTile, enemyType, tweenManager);
			enemyGroup.addActor(tempEnemy);
		}
	}
}
