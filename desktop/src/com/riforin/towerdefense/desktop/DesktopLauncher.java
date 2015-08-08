package com.riforin.towerdefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.riforin.towerdefense.towerdefense;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tower Defense";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new towerdefense(), config);
	}
}
