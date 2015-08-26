package com.riforin.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.BaseTween;

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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.riforin.gameobjects.Enemy;
import com.riforin.gameobjects.EnemyHandler;
import com.riforin.gameobjects.EnemyTween;
import com.riforin.gameobjects.Tile;
import com.riforin.gameobjects.Tile.TILETYPE;
import com.riforin.gameobjects.TileMap;
import com.riforin.gameworld.GameWorld;
import com.riforin.tdhelpers.AssetLoader;
import com.riforin.ui.HUD;
import com.riforin.ui.UIManager;

public class GameScreen implements Screen {

	private GameWorld world;
	private float runTime;

	// Tile things
	private TiledMap tiledMap;
	private float tileSize;
	private TileMap currentMap;
	private Tile startingTile;
	
	private Stage stage;
	
	private int[] selected;
	
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;
	private Group tileGroup;			// Furthest back group
	private Group enemyGroup;
	private Group unitGroup;			// Units.
	private Group uiGroup;				// Renders ui and backgrounds.
	
	// TODO: Put these two together? 
	// Issue is that the EnemyTween has to have access to the manager.
	private EnemyHandler enemyHandler; // Handles enemy pathing/waves.
	private TweenManager enemyTweenManager;  // Handles the movement of enemies.
	
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	
	private UIManager uiManager;
	private HUD hud;
	
	public GameScreen() {
		Gdx.app.log("GameScreen", "created");
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
		
		tileGroup = new Group();
		enemyGroup = new Group();
		unitGroup = new Group();
		uiGroup = new Group();
		
		stage = new Stage(new FitViewport(800, 480, camera));
		
		stage.addActor(tileGroup);
		stage.addActor(enemyGroup);
		stage.addActor(unitGroup);
		stage.addActor(uiGroup);
		
		Gdx.input.setInputProcessor(stage);
		// Initialize the UIManager
		uiManager = new UIManager(uiGroup, unitGroup);
		
		// Initialize an empty map and fill it
		currentMap = new TileMap(25, 15);		
		loadMap();
				
		// Initialize enemy handler.
		enemyHandler = new EnemyHandler(currentMap, 0, startingTile, enemyGroup);
		setupEnemyTween();
				
		// Initialize the HUD
		hud = new HUD(uiGroup, enemyHandler); 
		
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
				thisTile = new Tile(col, row, TILETYPE.obstacle, selected, uiManager);
				
				// Load tower spots
				if (towerLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.tower, selected, uiManager);
				}
				
				// Load the path tiles
				if (pathLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.path, selected, uiManager);
				}

				// Load the start tile
				if (startLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.start, selected, uiManager);
					startingTile = thisTile;
				}
				
				// Load the end tile
				if (endLayer.getCell(col, row) != null) {
					thisTile = new Tile(col, row, TILETYPE.end, selected, uiManager);
				}
				
				// TODO: Remove this and see if it's necessary
				thisTile.setTouchable(Touchable.enabled);
				tileGroup.addActor(thisTile);
				
				currentMap.place(thisTile, col, row);
			}
		}
		// Assign directions to every tile.
		currentMap.assignNextTiles(startingTile);
	}
	
	private void setupEnemyTween() {
		Tween.registerAccessor(Enemy.class, new EnemyTween());
		enemyTweenManager = new TweenManager();
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		
		// Fills screen with black to prevent flickering.
		Gdx.gl.glClearColor(0, 0, 0, 0); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		
		stage.act(delta);
		stage.draw();
		
		
	}
	
	public EnemyHandler getEnemyHandler() {
		return enemyHandler;
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
