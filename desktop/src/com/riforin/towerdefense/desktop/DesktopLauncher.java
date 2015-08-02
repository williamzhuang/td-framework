package com.riforin.towerdefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.riforin.towerdefense.towerdefense;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Myotd";
		config.width = 408;
		config.height = 272;
		new LwjglApplication(new towerdefense(), config);
	}
}
