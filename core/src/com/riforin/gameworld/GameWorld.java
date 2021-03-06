package com.riforin.gameworld;

import com.riforin.gameobjects.TileMap;
import com.riforin.gameobjects.TowerHandler;

/** 
 * Helper class that updates all objects. 
 * @author William Zhuang
 *
 */
public class GameWorld {
	
	TileMap currentMap;
	TowerHandler turretHandler;
	
	/**
	 * GameWorld constructor.
	 * @param midPointX X coordinate of the midpoint of the screen. 
	 */
	public GameWorld(TileMap currentMap0) {
		// TODO: Initialize all object handlers.
		currentMap = currentMap0;
		
	}
	
	/**
	 * Updates all objects and object handlers.
	 * @param delta
	 */
	public void update(float delta) {
		// TODO: Call update methods of all objects and handlers. 
	}
}
