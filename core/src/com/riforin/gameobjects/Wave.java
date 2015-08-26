package com.riforin.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.utils.Array;
import com.riforin.gameobjects.Enemy.ENEMYTYPE;

public class Wave {
	
	ArrayList<ENEMYTYPE> enemies;
	ArrayList<Integer> numbers;
	int waveNumber;
	
	public Wave(int waveNumber, ArrayList<ENEMYTYPE> enemytypes, ArrayList<Integer> numbers) {
		this.waveNumber = waveNumber;
		this.enemies = enemies;
		this.numbers = numbers;
	}
	
	public ArrayList<ENEMYTYPE> getEnemies() {
		return enemies;
	}
	
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}
}
