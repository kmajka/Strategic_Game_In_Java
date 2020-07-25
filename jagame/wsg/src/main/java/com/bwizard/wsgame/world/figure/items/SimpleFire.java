package com.bwizard.wsgame.world.figure.items;

import com.bwizard.wsgame.world.figure.GameFigure;

import com.bwizard.cegame.state.StateInfoManager;

public class SimpleFire extends GameFigure {
	
	public SimpleFire() {	
		initialize();
	}
	
	public SimpleFire(int x, int y, StateInfoManager stateInfoManager) {
		super(x , y, stateInfoManager);
		initialize();

	}
	
	private void initialize() {
		long timeFire= 1000;
		
		frameManager.addFrame(getImageSprite("f_fire_1.png"), timeFire);
		frameManager.addFrame(getImageSprite("f_fire_2.png"), timeFire);
	}
}
