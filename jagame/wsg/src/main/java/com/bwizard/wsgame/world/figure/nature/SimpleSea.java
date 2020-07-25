package com.bwizard.wsgame.world.figure.nature;

import com.bwizard.wsgame.world.figure.GameFigure;

import com.bwizard.cegame.state.StateInfoManager;

public class SimpleSea extends GameFigure {
	
	public SimpleSea() {
		initialize();
	}
	
	public SimpleSea(int x, int y, StateInfoManager stateInfoManager) {
		super(x , y, stateInfoManager);
		initialize();
	}
	
	private void initialize() {
		frameManager.addFrame(getImageSprite("xxx.png"), 0);
	}
	
}

