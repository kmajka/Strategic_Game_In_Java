package com.bwizard.wsgame.world.figure.buildings;

import com.bwizard.wsgame.world.figure.GameFigure;
import com.bwizard.cegame.state.StateInfoManager;

public class Box extends GameFigure {
	
	public Box() {
		initialize();
	}
	
	public Box(int x, int y, StateInfoManager stateInfoManager) {
		super(x , y, stateInfoManager);
		initialize();
	}
	
	private void initialize() {
		
		long time= 100;
		
		frameManager.addFrame(getImageSprite("box.png"), time);
	}
	
}
