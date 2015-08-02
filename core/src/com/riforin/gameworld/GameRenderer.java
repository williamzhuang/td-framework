package com.riforin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.riforin.tdhelpers.AssetLoader;

/** 
 * Helper method that renders all graphics. 
 * @author William Zhuang
 *
 */
public class GameRenderer {
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointX;
	private int gameHeight;

	// Game Objects

	// Game Assets
	
	/** 
	 * 
	 * @param world GameWorld that handles all objects/updating.
	 * @param gameHeight Height of the game. 
	 * @param midPointY Midpoint of the screen. 
	 */
	public GameRenderer(GameWorld world, int gameHeight, int midPointX) {
		myWorld = world;
		initGameObjects();
		initGameAssets();

		this.gameHeight = gameHeight;
		this.midPointX = midPointX;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, 204);

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}

	public void render(float runTime) {

		// Fills screen with black to prevent flickering.
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void initGameObjects() {
		
	}

	private void initGameAssets() {

	}
}
