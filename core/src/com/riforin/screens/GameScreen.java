package com.riforin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.riforin.gameobjects.Tile;
import com.riforin.gameobjects.Tile.Type;
import com.riforin.gameobjects.TileMap;
import com.riforin.gameworld.GameRenderer;
import com.riforin.gameworld.GameWorld;
import com.riforin.tdhelpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer mapRenderer;
	private float tileSize;
	
	private TileMap currentMap;

	public GameScreen() {

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameHeight = 136;
		float gameWidth = screenWidth / (screenHeight / gameHeight);

		int midPointY = (int) (gameHeight / 2);

		loadMap();

		// Pass the map into world
		world = new GameWorld(midPointY, currentMap);
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
	}

	private void loadMap() {
		// Load map.
		// TODO: Modify this to account for multiple maps
		
		tiledMap = new TmxMapLoader().load("res/maps/test.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		// Get information from the map.
		TiledMapTileLayer graphicalLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Graphical");
		TiledMapTileLayer pathLayer = (TiledMapTileLayer) tiledMap.getLayers()
				.get("Path");
		TiledMapTileLayer towerLayer = (TiledMapTileLayer) tiledMap
				.getLayers().get("TowerSpots");
		TiledMapTileLayer startLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Start");
		TiledMapTileLayer endLayer = (TiledMapTileLayer) tiledMap.getLayers().get("End");
		
		Tile thisTile = null;
		// Iterate through very square
		for (int row = 0; row < graphicalLayer.getHeight(); row++) {
			for (int col = 0; col < graphicalLayer.getWidth(); col++) {

				// Load tower spots
				if (towerLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, Type.tower);
				}
				
				// Load the path tiles
				if (pathLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, Type.path); 
				}

				// Load the start tile
				if (startLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, Type.start);
					continue;
				}
				
				// Load the end tile
				if (endLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, Type.end);
				}
				
				// If there is no tile, assign an obstacle tile
				thisTile = new Tile(col, row, Type.obstacle);
				
				currentMap.place(thisTile, col, row);
			}
		}
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void dispose() {

	}

}
