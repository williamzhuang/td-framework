package com.riforin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.riforin.tdhelpers.AssetLoader;

/** 
 * Helper method that renders all graphics. 
 * @author William Zhuang
 *
 */
public class GameRenderer {
	private GameWorld myWorld;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointX;
	private int gameHeight;
	
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	
	private int[] selected;

	// Game Objects

	// Game Assets
	
	/** 
	 * 
	 * @param world GameWorld that handles all objects/updating.
	 * @param gameHeight Height of the game. 
	 * @param midPointY Midpoint of the screen. 
	 */
	public GameRenderer(GameWorld world, int gameHeight, int midPointX, TiledMap levelMap, Stage stage, int[] selected) {
		myWorld = world;
		initGameObjects();
		initGameAssets();
		this.selected = selected;

		this.gameHeight = gameHeight;
		this.midPointX = midPointX;

		camera = new OrthographicCamera();
		// Map is displayed at double its actual size. 
		camera.setToOrtho(false, 800, 480);

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(camera.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		// Attach the map's level to a renderer.
		tiledMapRenderer = new OrthogonalTiledMapRenderer(levelMap);
	}

	public void render(float runTime) {

		// Fills screen with black to prevent flickering.
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Updates camera position
		// TODO: Alter the screen size and allow the user to move the camera
		camera.update();
		
		// Display the base TiledMap
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		
		// Begin SpriteBatch
		batcher.begin();
		batcher.draw(AssetLoader.selector, selected[0], selected[1], 32, 32);
		batcher.end();
		// Display the selector upon touch.
		
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}

	private void initGameObjects() {
		
	}

	private void initGameAssets() {

	}
}
