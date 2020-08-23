package com.bwizard.wsgame.world.figure.nature;

import com.bwizard.wsgame.world.figure.GameFigure;

import com.bwizard.cegame.state.StateInfoGame;

public class SimpleSea extends GameFigure {
	
	public SimpleSea() {
		initialize();
	}
	
	public SimpleSea(int x, int y, StateInfoGame stateInfoGame) {
		super(x , y, stateInfoGame);
		initialize();
	}
	
	private void initialize() {
		frameManager.addFrame(getImageSprite("xxx.png"), 0);
	}
	
}

