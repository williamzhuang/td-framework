package com.riforin.gameobjects;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Handler class that updates and handles turrets.
 * @author William Zhuang
 *
 */
public class TowerHandler extends Actor {
	
	public TileMap tileMap;
	public ArrayList<Tower> towerList;
	public ArrayList<Enemy> enemyList;
	private Group unitGroup;
	/** 
	 * TurretHandler constructor. 
	 * @param tileMap
	 */
	public TowerHandler(Group unitGroup) {
		this.unitGroup = unitGroup;
		unitGroup.addActor(this);
		towerList = new ArrayList<Tower>();
	}
	
	/**
	 * Places a turret at a position
	 * @param x X tile coordinate
	 * @param y Y tile coordinate
	 */
	public void placeTower(Tower tower) {
		towerList.add(tower);
		unitGroup.addActor(tower);
	}
	
	public void act(float delta) {
		
		// TODO: Ensure this algorithm allows for the most effective towers.
		for (Tower tower : towerList) {
			for (Enemy enemy : enemyList) {
				if (Intersector.overlaps(tower.getRangeCircle(), enemy.getBoundingCircle())) {
					tower.setTarget(enemy);
				}
			}
		}
	}
	
	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}
	
	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
}
