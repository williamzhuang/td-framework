package com.riforin.gameobjects;

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
}
