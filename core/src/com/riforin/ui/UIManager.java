package com.riforin.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.riforin.gameobjects.Infantry;
import com.riforin.gameobjects.Tile;
import com.riforin.gameobjects.Tile.TILETYPE;
import com.riforin.gameobjects.Tower;
import com.riforin.gameobjects.TowerHandler;
import com.riforin.ui.UIButton.BUTTONTYPE;

public class UIManager {
	
	private Tile chosenTile;
	
	private UIButton ring;
	private UIButton newtower;
	private UIButton newinfantry;
	private UIButton destroy;
	private UIButton upgrade;
	private TowerHandler towerHandler;
	
	private Group uiGroup;
	
	public UIManager(Group uiGroup, TowerHandler towerHandler) {
		
		this.uiGroup = uiGroup;
		this.towerHandler = towerHandler;
		
		ring = new UIButton(BUTTONTYPE.wheel, this);
		newtower = new UIButton(BUTTONTYPE.tower, this);
		newinfantry = new UIButton(BUTTONTYPE.infantry, this);
		destroy = new UIButton(BUTTONTYPE.destroy, this);
		upgrade = new UIButton(BUTTONTYPE.upgrade, this);
		
		ring.setVisible(false);
		newtower.setVisible(false);
		newinfantry.setVisible(false);
		destroy.setVisible(false);
		upgrade.setVisible(false);
		
		uiGroup.addActor(ring);
		uiGroup.addActor(newtower);
		uiGroup.addActor(newinfantry);
		uiGroup.addActor(destroy);
		uiGroup.addActor(upgrade);

	}
	
	public void show(Tile tile) {
		chosenTile = tile;
		
		ring.show(tile);
		if (chosenTile.getType() == TILETYPE.tower) {
			if (chosenTile.isOccupied()) {
				displayExistingMenu(tile);
			} else {
				displayVacantMenu(tile);
			}
		}
	}
	
	private void displayVacantMenu(Tile tile) {
		newtower.show(tile);
		newinfantry.show(tile);
	}
	
	private void displayExistingMenu(Tile tile) { 
		destroy.show(tile);
		upgrade.show(tile);
	}
	
	public void createTower(Tower tower) {
		towerHandler.placeTower(tower);
	}
	
	public void createInfantry(Infantry infantry) {
		towerHandler.placeTower(infantry);
	}
	
	public void openUpgradeMenu() {
		// TODO: Make a list of possible parts.
	}
	
	public void salvageTower() {
		// TODO: Destroy the tower and return parts.
	}
	
	public void closeAll() {
		ring.setVisible(false);
		newtower.setVisible(false);
		newinfantry.setVisible(false);
		destroy.setVisible(false);
		upgrade.setVisible(false);
	}
}
