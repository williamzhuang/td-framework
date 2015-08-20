package com.riforin.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.utils.Array;
import com.riforin.gameobjects.Enemy.ENEMYTYPE;

public class Wave {
	
	ENEMYTYPE[] enemies;
	int[] numbers;
	int waveNumber;
	
	public Wave(int waveNumber, ENEMYTYPE[] enemytypes, int[] numbers) {
		this.waveNumber = waveNumber;
		this.enemies = enemies;
		this.numbers = numbers;
	}
	
	public ENEMYTYPE[] getEnemies() {
		return enemies;
	}
	
	public int[] getNumbers() {
		return numbers;
	}
}
