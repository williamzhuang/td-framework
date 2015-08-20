package com.riforin.gameobjects;


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
		
		while (currentTile.getType() != TILETYPE.end) {
			int currentTileX = currentTile.getTileX();
			int currentTileY = currentTile.getTileY();
			
			Tile leftTile = getLeftTile(currentTile);
			Tile rightTile = getRightTile(currentTile);
			Tile upTile = getUpTile(currentTile);
			Tile downTile = getDownTile(currentTile);
			
			// TODO: Branching paths
			// Search in four directions around this one and assign directions to every single one.
			if (leftTile.getType() == TILETYPE.path) {
				if (leftTile.getNextTile() == null) {
					currentTile.setNextTile(leftTile);
					currentTile = leftTile;
					continue;
				}
			} 
			if (rightTile.getType() == TILETYPE.path) {
				if (rightTile.getNextTile() == null) {
					currentTile.setNextTile(rightTile);
					currentTile = rightTile;
					continue;
				}
			}
			if (upTile.getType() == TILETYPE.path) {
				if (upTile.getNextTile() == null) {
					upTile.setNextTile(upTile);
					currentTile = upTile;
					continue;
				}
			}
			if (downTile.getType() == TILETYPE.path){
				if (downTile.getNextTile() == null) {
					downTile.setNextTile(downTile);
					currentTile = downTile;
					continue;
				}
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
