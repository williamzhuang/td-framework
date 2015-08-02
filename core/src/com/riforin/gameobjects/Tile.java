package com.riforin.gameobjects;

/**
 * Class for a tile. Tiles are designated squares within
 * a tileMap.
 * @author William Zhuang
 *
 */

public class Tile {
	public int x;					// x Location of tile to draw.
	public int y;					// y location of tile to draw.
	public int tileX;				// tileMap X coordinate.
	public int tileY;				// tileMap Y coordinate.
	public boolean occupied;
	public final int TILE_SIZE = 16;
	public Turret turret;			// Designates what turret is on this tile.
	
	public enum Type {
		start, end, path, obstacle, tower
	}
	public Type type;
	
	/** Tile constructor. */
	public Tile(int x0, int y0, Type type0) {
		x = x0 * TILE_SIZE;
		y = y0 * TILE_SIZE;
		tileX = x0;
		tileY = y0;
		occupied = false;
		turret = null;
		type = type0;
	}

	public boolean isOccupied() {
		return occupied;
	}
	
	/** 
	 * Places a turret in this tile. 
	 * @param turret0 Turret to be placed in this tile. 
	 */
	public void occupy(Turret turret0) {
		occupied = true;
		turret = turret0;
	}
	
	/**
	 * Removes turret occupying this tile. 
	 * @return Returns the turret occupying this space or null
	 * 		   if no such turret exists.
	 */
	public Turret remove() {
		if (isOccupied()) {
			turret = null;
			occupied = false;
		} 
		
		return null;
	}
	
}
