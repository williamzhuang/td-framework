package com.riforin.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.riforin.gameobjects.Enemy;
import com.riforin.gameobjects.Enemy.ENEMYTYPE;
/**
 * Handles spawning and behavior of all enemies.
 * @author William Zhuang
 *
 */
public class EnemyHandler {
	
	Tile startingTile;
	TileMap tileMap;
	int startTileX;
	int startTileY;
	Group enemyGroup;
	
	ArrayList<Wave> waveList;
	// TODO: Some UI Buttons that start the wave.
	/** EnemyHandler constructor */
	public EnemyHandler(TileMap tileMap, int mapNumber, Tile startingTile, Group enemyGroup) {
		this.tileMap = tileMap;
		
		this.startingTile = startingTile;
		startTileX = startingTile.getTileX();
		startTileY = startingTile.getTileY();
		
		waveList = new ArrayList<Wave>();
		this.enemyGroup = enemyGroup;
	}
	
	private void loadWaves(int mapNumber) {
		if (mapNumber == 1) {
			// TODO: make this specific to the map number.
			ENEMYTYPE[] enemyTypes = {ENEMYTYPE.flame};
			int[] numbers = {5};
			Wave wave1 = new Wave(1, enemyTypes, numbers);
			waveList.add(wave1);
		}
	}
	
	// Spawns enemies 
	// Have a button from the UI that starts the wave.
	// Calls draw methods and puts enemy actors on the screen.
	public void spawn() {
		// TODO: Add a delay between waves.
		int[] numbers;
		ENEMYTYPE[] enemies;
		
		for (Wave wave:waveList) {
			int counter = 0;
			enemies = wave.getEnemies();
			numbers = wave.getNumbers();
			for (ENEMYTYPE enemy:enemies) {
				singleSummon(enemy, numbers[counter]);
				counter += 1;
			}
		}
	}
	
	// Summons a single type of enemy a single number of times 
	public void singleSummon(ENEMYTYPE enemy, int number) {
		// TODO: Delay between summons.
		ENEMYTYPE enemyType = enemy;
		for (int i = 0; i < number; i += 1) {
			Enemy tempEnemy = new Enemy(startingTile, enemyType);
			enemyGroup.addActor(tempEnemy);
		}
	}
	
}
