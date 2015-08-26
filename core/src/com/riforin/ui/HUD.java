package com.riforin.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.riforin.gameobjects.EnemyHandler;
import com.riforin.ui.HUDButton.HUDBUTTONTYPE;

public class HUD {
	
	private HUDButton nextWave;
	private Group uiGroup;
	
	public HUD(Group uiGroup, EnemyHandler enemyHandler) {
		// Setup HUD elements 
		this.uiGroup = uiGroup;
		
		// Instantiating buttons.
		nextWave = new HUDButton(HUDBUTTONTYPE.nextWave, enemyHandler);
		
		// Inserting elements into uiGroup
		uiGroup.addActor(nextWave);
		
	}
	
	
}
