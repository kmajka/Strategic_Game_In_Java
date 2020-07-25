package com.bwizard.wsgame.world.figure.buildings;

import com.bwizard.wsgame.world.figure.GameFigure;

import java.awt.Color;
import java.awt.Graphics;

import com.bwizard.cegame.state.StateInfoManager;
import com.bwizard.cegame.tools.DrawManager;

public class SimpleCastle extends GameFigure {
	
	public SimpleCastle() {	
		initialize();
	}
	
	@Override
	public void updateEditor() {
		setPlacedManualy(true);
		canMove(false);
		canFocus(true);
		//setActivityFigure(ActivityBaseFigure.PLACED_MANUALY);
	}
	
	public SimpleCastle(int x, int y, StateInfoManager stateInfoManager) {
		super(x , y, stateInfoManager);
		initialize();
	}
	
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		if(selected) {
			stateInfoManager.getPanelLayoutManager().setVisiblePanelFromGroup("panelCastle", true);
		} else {
			stateInfoManager.getPanelLayoutManager().setVisiblePanelFromGroup("panelCastle", false);
		}
	}
	
	@Override
	public boolean isExistOnComponentMap() {
		return true;
	}
	
	@Override
	public void DrawOnComponentMap(Graphics g, int placeDrawMapX, int placeDrawMapY, float scaleMapWidth, float scaleMapHeight) {
		
		int x = (int)(placeDrawMapX + (getMapPositionX() * scaleMapWidth)); 
		int y = (int)(placeDrawMapY + (getMapPositionY() * scaleMapHeight)); 
				 
		DrawManager.fillRect(g, x, y, (int)(getWidth() * scaleMapWidth) , (int)(getHeight() * scaleMapHeight), Color.RED);
	}
	
	private void initialize() {
		canFocus(true);
		frameManager.addFrame(getImageSprite("castle1.png"), 0);

	}
	
}
