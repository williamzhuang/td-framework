package com.riforin.gameobjects;


import com.badlogic.gdx.Gdx;
import com.riforin.gameobjects.Tile.TILETYPE;

public class TileMap {
	
	public Tile[][] tileArray;
	
	public TileMap(int colsize, int rowsize) {
		tileArray = new Tile[colsize][rowsize];
	}
	
	public void place(Tile tile, int x, int y) {
		tileArray[x][y] = tile;
	}
	
	public Tile get(int x, int y) {
		return tileArray[x][y];
	}
	
	// Iterate through all path tiles and assign a direction to them.
	// The direction points in the direction where the 
	public void assignNextTiles(Tile startingTile) {
		
		Tile currentTile = startingTile;
		Tile leftTile = getLeftTile(startingTile);
		Tile rightTile = getRightTile(startingTile);
		Tile upTile = getUpTile(startingTile);
		Tile downTile = getDownTile(startingTile);
		
		
		while (currentTile.getType() != TILETYPE.end) {
			
			leftTile = getLeftTile(currentTile);
			rightTile = getRightTile(currentTile);
			upTile = getUpTile(currentTile);
			downTile = getDownTile(currentTile);
			// TODO: Branching paths
			// Search in four directions around this one and assign directions to every single one.
			if ((leftTile.getType() == TILETYPE.path) && (leftTile.getNextTile() == null)) {
				currentTile.setNextTile(leftTile);
				currentTile = leftTile;
				continue;
			}
			
			if (((rightTile.getType() == TILETYPE.path) && (rightTile.getNextTile() == null)) || (rightTile.getType() == TILETYPE.end)) {
				currentTile.setNextTile(rightTile);
				currentTile = rightTile;
				continue;
			}
			
			if ((downTile.getType() == TILETYPE.path) && (downTile.getNextTile() == null)) {
				currentTile.setNextTile(downTile);
				currentTile = downTile;
				continue;
			}
			
			if ((upTile.getType() == TILETYPE.path) && (upTile.getNextTile() == null)) {
				currentTile.setNextTile(upTile);
				currentTile = upTile;
				continue;
			}
		}
	}
	
	private Tile getLeftTile(Tile tile) {
		return tileArray[tile.getTileX() - 1][tile.getTileY()];
	}
	
	private Tile getRightTile(Tile tile) {
		return tileArray[tile.getTileX() + 1][tile.getTileY()];
	}
	
	private Tile getUpTile(Tile tile) {
		return tileArray[tile.getTileX()][tile.getTileY() + 1];
	}
	
	private Tile getDownTile(Tile tile) {
		return tileArray[tile.getTileX()][tile.getTileY() - 1];
	}
}
