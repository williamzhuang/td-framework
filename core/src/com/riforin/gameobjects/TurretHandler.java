package com.riforin.gameobjects;
import java.util.ArrayList;

/**
 * Handler class that updates and handles turrets.
 * @author William Zhuang
 *
 */
public class TurretHandler {
	
	public TileMap tileMap;
	public ArrayList<Turret> turretArray;
	
	/** 
	 * TurretHandler constructor. 
	 * @param tileMap
	 */
	public TurretHandler(TileMap tileMap0) {
		tileMap = tileMap0;
	}
	
	/**
	 * Updates all turrets and their related properties.
	 * @param delta
	 */
	public void update(float delta) {
		for (Turret turret : turretArray) {
			turret.update(delta);
		}
	}
	
	/**
	 * Places a turret at a position
	 * @param x X tile coordinate
	 * @param y Y tile coordinate
	 */
	public void placeTurret(int x, int y, Turret turret) {
		tileMap.get(x, y).occupy(turret);
		turretArray.add(turret);
	}
	
}
