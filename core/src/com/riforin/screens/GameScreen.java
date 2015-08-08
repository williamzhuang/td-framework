package com.riforin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.riforin.gameobjects.Tile;
import com.riforin.gameobjects.Tile.TILETYPE;
import com.riforin.gameobjects.TileMap;
import com.riforin.gameworld.GameWorld;
import com.riforin.tdhelpers.AssetLoader;
import com.riforin.ui.UIManager;

public class GameScreen implements Screen {

	private GameWorld world;
	private float runTime;

	private TiledMap tiledMap;
	private float tileSize;
	
	private TileMap currentMap;
	
	private Stage stage;
	
	private int[] selected;
	
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;
	
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	
	private UIManager manager;
	
	public GameScreen() {
		
		// Information about the screen. 
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameHeight = 480;
		float gameWidth = 800;
		
		// Initialize selector coordinates.
		selected = new int[2];
		selected[0] = -1;
		selected[1] = -1;
		
		// Setting up the stage and input processing.
		// Stage handles the UI processing.
		// inputHandler handles non-UI inputs (e.g. touches on tiles.)
		camera = new OrthographicCamera();
		
		// Map is displayed at double its actual size. 
		camera.setToOrtho(false);
		
		stage = new Stage(new FitViewport(800, 480, camera));
		Gdx.input.setInputProcessor(stage);
		// Initialize the UI Manager
		manager = new UIManager(stage);
		
		// Initialize an empty map and load it
		currentMap = new TileMap(25, 15);
		
		loadMap();
		
		// Pass the map into world
		world = new GameWorld(currentMap);
		
		// Rendering related processes

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(camera.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		// Attach the map's level to a renderer.
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		
	}

	private void loadMap() {
		// Load map.
		// TODO: Modify this to account for multiple maps
		
		tiledMap = new TmxMapLoader().load("test.tmx");
		

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
				
				// If there is no tile, assign an obstacle tile
				thisTile = new Tile(col, row, TILETYPE.obstacle, selected, manager);
				
				// Load tower spots
				if (towerLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.tower, selected, manager);
				}
				
				// Load the path tiles
				if (pathLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.path, selected, manager); 
				}

				// Load the start tile
				if (startLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.start, selected, manager);
					continue;
				}
				
				// Load the end tile
				if (endLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.end, selected, manager);
					continue;
				}
				
				// TODO: Remove this and see if it's necessary
				thisTile.setTouchable(Touchable.enabled);
				stage.addActor(thisTile);
				
				currentMap.place(thisTile, col, row);
			}
		}
		
		
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		stage.act(delta);
		
		
		// Fills screen with black to prevent flickering.
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		
		
		
		batcher.begin();
		batcher.draw(AssetLoader.selector, selected[0], selected[1], 32, 32);
		
		batcher.end();
		stage.draw();
		
		
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
