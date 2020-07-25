package com.bwizard.wsgame.world.figure.nature;

import com.bwizard.wsgame.world.figure.GameFigure;

import com.bwizard.cegame.state.StateInfoManager;

public class SimpleFlower extends GameFigure {
	
	public SimpleFlower() {
		initialize();
	}
	
	public SimpleFlower(int x, int y, StateInfoManager stateInfoManager) {
		super(x , y, stateInfoManager);
		initialize();
	}
	
	private void initialize() {
		frameManager.addFrame(getImageSprite("f_flower_1.png"), 0);
	}
	
}

