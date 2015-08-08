package com.riforin.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.riforin.tdhelpers.AssetLoader;
import com.riforin.ui.UIManager;

/**
 * Class for a tile. Tiles are designated squares within
 * a tileMap.
 * @author William Zhuang
 *
 */

public class Tile extends Actor{
	
	public Vector2 position;
	public int tileX;				// tileMap X coordinate.
	public int tileY;				// tileMap Y coordinate.
	public boolean occupied;
	public final int TILE_SIZE = 32;
	public Turret turret;			// Designates what turret is on this tile.
	private final UIManager manager;
	private Tile thisTile;			// Really weird way to do this because you cannot call this in the InputListener.
	
	public enum TILETYPE {
		start, end, path, obstacle, tower
	}
	public TILETYPE type;
	
	private int[] selectedCoords;
	
	/** Tile constructor. */
	public Tile(int x0, int y0, TILETYPE type0, int[] selectedCoords, final UIManager manager) {
		position = new Vector2(x0 * TILE_SIZE, y0 * TILE_SIZE);
		tileX = x0;
		tileY = y0;
		occupied = false;
		turret = null;
		type = type0;
		this.selectedCoords = selectedCoords;
		thisTile = this;
		this.manager = manager;
		
		// Actor information.
		setBounds(x0 * TILE_SIZE, y0 * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (type == TILETYPE.tower) {
					((Tile)event.getTarget()).selectedCoords[0] = (int) position.x;
					((Tile)event.getTarget()).selectedCoords[1] = (int) position.y;
					manager.show(thisTile);
				}
				return true;
			}
		});
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
	public Turret removeTurret() {
		if (isOccupied()) {
			turret = null;
			occupied = false;
		} 
		
		return null;
	}
	
	public TILETYPE getType() {
		return type;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
}
